package dev.dima.transactionservice.services.implementations;

import com.hazelcast.core.HazelcastInstance;
import dev.dima.transactionservice.exceptions.BalanceException;
import dev.dima.transactionservice.repositories.UserRepository;
import dev.dima.transactionservice.services.BalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ConcurrentMap;

@Service
@RequiredArgsConstructor
public class BalanceServiceImpl implements BalanceService {

    private final UserRepository userRepository;

    private HazelcastInstance hazelcastInstance;

    private ConcurrentMap<UUID,Integer> retrieveMap() {
        return hazelcastInstance.getMap("cache");
    }

    @Override
    public int getUserBalance(UUID userId) {
        Integer value = retrieveMap().get(userId);
        if(value != null) return value;

        try {
            Integer balance = userRepository.findBalanceById(userId);
            retrieveMap().put(userId, balance);
            return balance;
        } catch (Exception ex) {
            throw new BalanceException("Не удалось получить баланс пользователя");
        }
    }
}
