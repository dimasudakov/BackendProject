package dev.dima.authservice.services;

import dev.dima.authservice.dtos.requests.JwtRequest;
import dev.dima.authservice.dtos.requests.UserRequest;
import dev.dima.authservice.dtos.responses.JwtResponse;
import dev.dima.authservice.exceptions.*;
import dev.dima.authservice.models.JwtAuthentication;
import dev.dima.authservice.models.UserEntity;
import dev.dima.authservice.models.enums.Status;
import dev.dima.authservice.repositories.JwtTokenRedisRepository;
import dev.dima.authservice.utils.mappers.UserMapper;
import io.jsonwebtoken.Claims;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService{

    private final UserService userService;
    private final UserMapper userMapper;
    private final JwtTokenRedisRepository redisRepository;
    private final JwtProvider jwtProvider;

    public void register(@NonNull @Valid UserRequest userDto) {
        UserEntity userEntity = userMapper.toEntity(userDto);

        if(userService.getByPhoneNumber(userEntity.getPhoneNumber()).isPresent()) {
            throw new UserAlreadyExistsException(
                    "User with phone number: " +  userEntity.getPhoneNumber() + " already exist");
        }
        if(userService.getByEmail(userEntity.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException(
                    "User with email: " +  userEntity.getEmail() + " already exist");
        }
        final String hash = BCrypt.hashpw(userEntity.getPassword(), BCrypt.gensalt());
        userEntity.setPassword(hash);

        userEntity = userService.createUser(userEntity)
                .orElseThrow(UnexpectedException::new);
        try {
            createUserInBetService(userEntity);
        } catch (BetServiceNotAvailableException ex) {
            userService.deleteUser(userEntity);
            throw ex;
        }

    }

    public JwtResponse login(@NonNull JwtRequest authRequest) {

        final UserEntity user = (UserEntity) userService.loadUserByUsername(authRequest.getUsername());

        if(user.getStatus() != Status.ACTIVE) {
            throw new UserStatusException("User is blocked");
        }

        if (BCrypt.checkpw(authRequest.getPassword(), user.getPassword())) {
            final String accessToken = jwtProvider.generateAccessToken(user);
            final String refreshToken = jwtProvider.generateRefreshToken(user);
            redisRepository.saveRefreshToken(user.getId(), refreshToken);
            return new JwtResponse(accessToken, refreshToken);
        } else {
            throw new InvalidCredentialsException("Invalid login or password");
        }
    }

    public JwtResponse getAccessToken(@NonNull String refreshToken) {
        if (jwtProvider.validateRefreshToken(refreshToken)) {

            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final UUID userId = UUID.fromString(claims.getSubject());
            final String savedRefreshToken = redisRepository.getRefreshToken(userId);

            if (savedRefreshToken != null && savedRefreshToken.equals(refreshToken)) {
                final UserEntity user = userService.getById(userId)
                        .orElseThrow(() -> new UserNotFoundException("User not found"));

                if(user.getStatus() != Status.ACTIVE) {
                    throw new UserStatusException("User is blocked");
                }

                final String accessToken = jwtProvider.generateAccessToken(user);
                return new JwtResponse(accessToken, null);
            }
        }
        return new JwtResponse(null, null);
    }

    public JwtResponse refresh(@NonNull String refreshToken) {
        if (jwtProvider.validateRefreshToken(refreshToken)) {

            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final UUID userId = UUID.fromString(claims.getSubject());
            final String savedRefreshToken = redisRepository.getRefreshToken(userId);

            if (savedRefreshToken != null && savedRefreshToken.equals(refreshToken)) {
                final UserEntity user = userService.getById(userId)
                        .orElseThrow(() -> new UserNotFoundException("User not found"));
                final String newAccessToken = jwtProvider.generateAccessToken(user);
                final String newRefreshToken = jwtProvider.generateRefreshToken(user);
                redisRepository.saveRefreshToken(user.getId(), refreshToken);
                return new JwtResponse(newAccessToken, newRefreshToken);
            }
        }

        throw new RefreshTokenException(refreshToken, "Invalid JWT refresh token");
    }

    public JwtAuthentication getAuthInfo() {
        return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }


    public void createUserInBetService(UserEntity userEntity) {
        RestTemplate restTemplate = new RestTemplate();
        String betServiceUrl = "http://bet-service:8081/api/betting/create-user";
        log.info(betServiceUrl);

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            String userJson = "{\"id\": \"" + userEntity.getId() + "\"}";

            HttpEntity<String> requestEntity = new HttpEntity<>(userJson, headers);

            restTemplate.exchange(betServiceUrl, HttpMethod.POST, requestEntity, String.class);

        } catch (Exception e) {
            throw new BetServiceNotAvailableException("Не удалось получить ответ от сервиса бизнес логики");
        }
    }


}