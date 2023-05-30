package dev.dima.betservice.dtos.requests;

import dev.dima.betservice.dtos.enums.ResultType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EventOutcomeResultRequest {

    @NotNull
    private ResultType result;
}
