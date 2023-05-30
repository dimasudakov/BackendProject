package dev.dima.betservice.api;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/betting/admin")
public interface AdminApi {

    @PutMapping("/event/block/{eventId}")
    ResponseEntity<String> blockEvent(@PathVariable UUID eventId);

    @PutMapping("/event/unblock/{eventId}")
    ResponseEntity<String> unblockEvent(@PathVariable UUID eventId);


    @PutMapping("/bet/block/{betId}")
    ResponseEntity<String> blockBet(@PathVariable UUID betId);

    @PutMapping("/bet/unblock/{betId}")
    ResponseEntity<String> unblockBet(@PathVariable UUID betId);


}
