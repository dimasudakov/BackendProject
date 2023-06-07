package dev.dima.transactionservice.dtos.requests;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;


@Getter
@RequiredArgsConstructor
public class CardRequest {

    private String cardNumber;

    private String cardholderName;

    private String expirationMonth;

    private String expirationYear;

    private String cardType;

    private String cvvCode;

    private UserRequest user;
}
