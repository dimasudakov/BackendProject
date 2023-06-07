package dev.dima.transactionservice.services;


import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface BalanceService {

    int getUserBalance(UUID userId);
}
