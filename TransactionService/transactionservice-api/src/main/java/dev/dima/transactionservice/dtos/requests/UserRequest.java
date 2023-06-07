package dev.dima.transactionservice.dtos.requests;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;



@Getter
@RequiredArgsConstructor
public class UserRequest {

    private UUID id;

}
