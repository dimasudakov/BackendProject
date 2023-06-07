package dev.dima.transactionservice.api;


import dev.dima.transactionservice.dtos.requests.TransactionRequest;
import dev.dima.transactionservice.dtos.responses.TransactionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/transaction/user/")
public interface TransactionApi {

    @PostMapping("/create")
    ResponseEntity<TransactionResponse> createTransaction(@AuthenticationPrincipal UUID userId,
                                                          @RequestBody TransactionRequest transactionRequest);

    @GetMapping
    ResponseEntity<List<TransactionResponse>> getAllTransactionsByUserId(@AuthenticationPrincipal UUID userId);
}
