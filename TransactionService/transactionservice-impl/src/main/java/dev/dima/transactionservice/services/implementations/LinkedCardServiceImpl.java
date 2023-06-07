package dev.dima.transactionservice.services.implementations;

import dev.dima.transactionservice.dtos.requests.CardRequest;
import dev.dima.transactionservice.dtos.responses.CardResponse;
import dev.dima.transactionservice.exceptions.TransactionServiceException;
import dev.dima.transactionservice.models.LinkedCard;
import dev.dima.transactionservice.models.User;
import dev.dima.transactionservice.repositories.LinkedCardRepository;
import dev.dima.transactionservice.repositories.UserRepository;
import dev.dima.transactionservice.services.LinkedCardService;
import dev.dima.transactionservice.utils.mappers.CardMapper;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LinkedCardServiceImpl implements LinkedCardService {

    private final LinkedCardRepository linkedCardRepository;

    private final UserRepository userRepository;

    private final CardMapper cardMapper;

    @Override
    public CardResponse createLinkedCard(CardRequest card) {
        LinkedCard linkedCard = cardMapper.toEntity(card);
        linkedCard = linkedCardRepository.save(linkedCard);
        return cardMapper.toResponse(linkedCard);
    }

    @Override
    public List<CardResponse> getCardsByUser(UUID userId) {
        User user =  userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Не получилось получить привязанные карты"));

        return cardMapper.toListResponse(user.getLinkedCards());
    }

    @Override
    public void deleteLinkedCard(UUID userId, UUID cardId) {
        try {
            linkedCardRepository.deleteByUserIdAndId(userId, cardId);
        } catch (Exception ex) {
            throw new TransactionServiceException(HttpStatus.CONFLICT, "Не получилось");
        }
    }
}
