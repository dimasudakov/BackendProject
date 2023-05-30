package dev.dima.authservice.services;

import dev.dima.authservice.models.UserEntity;
import dev.dima.authservice.models.UserRole;
import dev.dima.authservice.repositories.UserRolesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserRolesService {

    private final UserRolesRepository userRolesRepository;

    public List<UserRole> findAllByUser(UserEntity user) {
        return userRolesRepository.findByUser(user);
    }

}