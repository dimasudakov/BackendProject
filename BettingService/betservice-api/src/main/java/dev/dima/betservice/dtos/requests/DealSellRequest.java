package dev.dima.betservice.dtos.requests;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class DealSellRequest {

    private UUID dealId;

    private double price;
}
