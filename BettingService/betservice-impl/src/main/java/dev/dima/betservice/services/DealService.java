package dev.dima.betservice.services;

import dev.dima.betservice.dtos.requests.DealRequest;
import dev.dima.betservice.dtos.requests.DealSellRequest;
import dev.dima.betservice.dtos.responses.DealResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public interface DealService {
    ResponseEntity<DealResponse> createDeal(DealRequest deal, UUID userId);

    ResponseEntity<DealResponse> getDealById(UUID dealId, UUID userId);

    ResponseEntity<List<DealResponse>> getDealsByUserIdAndStatus(UUID userId, Boolean active);

    ResponseEntity<List<DealResponse>> getDealsByUserId(UUID userId);

    ResponseEntity<DealResponse> sellDealById(UUID userId, DealSellRequest dealSellRequest);

    ResponseEntity<Double> getDealSellingPrice(UUID userId, UUID dealId);
}
