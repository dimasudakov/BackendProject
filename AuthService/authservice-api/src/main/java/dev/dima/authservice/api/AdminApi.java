package dev.dima.authservice.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/api/admin")
public interface AdminApi {

    @PutMapping("/users/block/{id}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<String> blockUser(@PathVariable UUID id);

    @PutMapping("/users/unblock/{id}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<String> unblockUser(@PathVariable UUID id);

}
