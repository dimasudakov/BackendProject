package dev.dima.transactionservice.utils.mappers;


import dev.dima.transactionservice.dtos.requests.CardRequest;
import dev.dima.transactionservice.dtos.responses.CardResponse;
import dev.dima.transactionservice.models.LinkedCard;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CardMapper {

    LinkedCard toEntity(CardRequest card);

    CardResponse toResponse(LinkedCard card);

    List<CardResponse> toListResponse(List<LinkedCard> linkedCards);
}
