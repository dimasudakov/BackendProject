package dev.dima.betservice.api.User;

import dev.dima.betservice.dtos.requests.DealRequest;
import dev.dima.betservice.dtos.responses.DealResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/betting/user")
public interface DealsApi {

    @PostMapping("/create-deal")
    ResponseEntity<DealResponse> createDeal(@Valid @RequestBody DealRequest deal,
                                            @AuthenticationPrincipal UUID userId);

    @GetMapping("/deals")
    ResponseEntity<List<DealResponse>> getDealsById(@AuthenticationPrincipal UUID userId);
}
