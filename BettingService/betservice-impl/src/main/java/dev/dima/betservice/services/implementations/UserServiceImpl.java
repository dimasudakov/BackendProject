package dev.dima.betservice.services.implementations;

import dev.dima.betservice.dtos.requests.UserRequest;
import dev.dima.betservice.dtos.responses.BalanceResponse;
import dev.dima.betservice.exceptions.NotFoundException;
import dev.dima.betservice.exceptions.UnexpectedException;
import dev.dima.betservice.models.User;
import dev.dima.betservice.repositories.UserRepository;
import dev.dima.betservice.services.UserService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void createUser(@NotNull UserRequest userRequest) {
        User user = new User();
        user.setId(userRequest.getId());
        user.setBalance(500);
        try {
            userRepository.save(user);
        } catch (Exception ex) {
            throw new UnexpectedException();
        }
    }

    @Override
    public void updateUser(@NotNull User user) {
        userRepository.save(user);
    }

    @Override
    public ResponseEntity<Integer> getBalance(@NotNull UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Пользователь не найден"));
        return ResponseEntity.ok(user.getBalance());
    }

    @Override
    public ResponseEntity<BalanceResponse> getBalanceWithCurrency(@NotNull UUID userId) {
        String rates = getExchangeRates();
        JSONObject json = new JSONObject(rates);
        JSONObject wapRates = json.getJSONObject("wap_rates");
        JSONArray data = wapRates.getJSONArray("data");

        double usd = data.getJSONArray(0).getDouble(4);
        double eur = data.getJSONArray(1).getDouble(4);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Пользователь не найден"));

        BalanceResponse balanceResponse = new BalanceResponse();
        balanceResponse.setRub(user.getBalance());
        balanceResponse.setUsd(user.getBalance() / usd);
        balanceResponse.setEur(user.getBalance() / eur);
        return ResponseEntity.ok(balanceResponse);
    }

    private String getExchangeRates() {
        try {
            String url = "https://iss.moex.com/iss/statistics/engines/currency/markets/selt/rates.json";
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

            RequestEntity<?> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, new URI(url));
            ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);

            return responseEntity.getBody();
        } catch (URISyntaxException e) {
            throw new UnexpectedException();
        }
    }
}
