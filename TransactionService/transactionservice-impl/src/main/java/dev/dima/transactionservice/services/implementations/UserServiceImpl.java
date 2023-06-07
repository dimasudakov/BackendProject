package dev.dima.transactionservice.services.implementations;


import dev.dima.transactionservice.dtos.requests.UserRequest;
import dev.dima.transactionservice.exceptions.UnexpectedException;
import dev.dima.transactionservice.models.User;
import dev.dima.transactionservice.repositories.UserRepository;
import dev.dima.transactionservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void createUser(UserRequest userRequest) {
        User user = new User();
        user.setId(userRequest.getId());
        user.setBalance(500);
        try {
            userRepository.save(user);
        } catch (Exception ex) {
            throw new UnexpectedException();
        }
    }
}
