package dev.dima.betservice.controllers.User;

import dev.dima.betservice.api.User.DealsApi;
import dev.dima.betservice.dtos.requests.DealRequest;
import dev.dima.betservice.dtos.responses.DealResponse;
import dev.dima.betservice.services.DealService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor
@RestController
public class DealsController implements DealsApi {

    private final DealService dealService;

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<DealResponse> createDeal(@Valid DealRequest dealRequest, UUID userId) {
        return dealService.createDeal(dealRequest, userId);
    }

    @Override
    public ResponseEntity<List<DealResponse>> getDealsById(UUID userId) {
        return dealService.getDealsByUserId(userId);
    }
}
