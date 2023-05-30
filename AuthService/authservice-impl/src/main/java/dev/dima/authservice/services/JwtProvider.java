package dev.dima.authservice.services;

import dev.dima.authservice.models.UserEntity;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Slf4j
@Component
public class JwtProvider {

    private final SecretKey jwtAccessSecret;
    private final SecretKey jwtRefreshSecret;
    private final int accessMinutesExpiredTime;
    private final int refreshDaysExpiredTime;

    public JwtProvider(
            @Value("${jwt.secret.access.key}") String jwtAccessSecret,
            @Value("${jwt.secret.refresh.key}") String jwtRefreshSecret,
            @Value("${jwt.secret.access.exp_minutes}") int accessMinutesExpiredTime,
            @Value("${jwt.secret.refresh.exp_days}") int refreshDaysExpiredTime

    ) {
        this.jwtAccessSecret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtAccessSecret));
        this.jwtRefreshSecret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtRefreshSecret));
        this.accessMinutesExpiredTime = accessMinutesExpiredTime;
        this.refreshDaysExpiredTime = refreshDaysExpiredTime;
    }

    public String generateAccessToken(@NonNull UserEntity user) {
        final LocalDateTime now = LocalDateTime.now();
        final Instant accessExpirationInstant = now.plusMinutes(accessMinutesExpiredTime).atZone(ZoneId.systemDefault()).toInstant();
        final Date accessExpiration = Date.from(accessExpirationInstant);
        return Jwts.builder()
                .setSubject(String.valueOf(user.getId()))
                .setExpiration(accessExpiration)
                .signWith(jwtAccessSecret)
                .claim("firstName", user.getFirstName())
                .claim("roles", user.getAuthorities())
                .compact();
    }

    public String generateRefreshToken(@NonNull UserEntity user) {
        final LocalDateTime now = LocalDateTime.now();
        final Instant refreshExpirationInstant = now.plusDays(refreshDaysExpiredTime).atZone(ZoneId.systemDefault()).toInstant();
        final Date refreshExpiration = Date.from(refreshExpirationInstant);
        return Jwts.builder()
                .setSubject(String.valueOf(user.getId()))
                .setExpiration(refreshExpiration)
                .signWith(jwtRefreshSecret)
                .compact();
    }

    public boolean validateAccessToken(@NonNull String accessToken) {
        return validateToken(accessToken, jwtAccessSecret);
    }

    public boolean validateRefreshToken(@NonNull String refreshToken) {
        return validateToken(refreshToken, jwtRefreshSecret);
    }

    private boolean validateToken(@NonNull String token, @NonNull Key secret) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secret)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException expEx) {
            log.error("Token expired", expEx);
        } catch (UnsupportedJwtException unsEx) {
            log.error("Unsupported jwt", unsEx);
        } catch (MalformedJwtException mjEx) {
            log.error("Malformed jwt", mjEx);
        } catch (Exception e) {
            log.error("invalid token", e);
        }
        return false;
    }

    public Claims getAccessClaims(@NonNull String token) {
        return getClaims(token, jwtAccessSecret);
    }

    public Claims getRefreshClaims(@NonNull String token) {
        return getClaims(token, jwtRefreshSecret);
    }

    private Claims getClaims(@NonNull String token, @NonNull Key secret) {
        return Jwts.parserBuilder()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}