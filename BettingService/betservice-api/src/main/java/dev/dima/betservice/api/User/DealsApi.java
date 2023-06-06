package dev.dima.betservice.api.User;

import dev.dima.betservice.dtos.requests.DealRequest;
import dev.dima.betservice.dtos.requests.DealSellRequest;
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

    @PostMapping("/deal/create")
    ResponseEntity<DealResponse> createDeal(@Valid @RequestBody DealRequest deal,
                                            @AuthenticationPrincipal UUID userId);

    @GetMapping("/deals")
    ResponseEntity<List<DealResponse>> getDealsById(@AuthenticationPrincipal UUID userId);

    @GetMapping("/deal/sell/{dealId}")
    ResponseEntity<Double> getDealSellingPrice(@AuthenticationPrincipal UUID userId,
                                               @PathVariable UUID dealId);

    @DeleteMapping("/deal/sell")
    ResponseEntity<DealResponse> sellDeal(@AuthenticationPrincipal UUID userId,
                                          @RequestBody DealSellRequest dealSellRequest);
}
