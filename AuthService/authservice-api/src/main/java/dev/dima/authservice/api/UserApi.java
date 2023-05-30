package dev.dima.authservice.api;

import dev.dima.authservice.dtos.responses.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public interface UserApi {

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<UserResponse> getUser();
}
