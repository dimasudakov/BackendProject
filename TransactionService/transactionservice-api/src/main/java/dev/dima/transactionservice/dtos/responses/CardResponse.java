package dev.dima.transactionservice.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class CardResponse {

    private UUID id;

    private String cardNumber;

    private String cardholderName;

    private String expirationMonth;

    private String expirationYear;

    private String cardType;

    private String cvvCode;

}
