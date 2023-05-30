package dev.dima.authservice.repositories;

import dev.dima.authservice.models.UserEntity;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    @Query("SELECT u FROM UserEntity u JOIN FETCH u.userRoles r WHERE u.email = ?1")
    Optional<UserEntity> findByEmail(String login);

    @Query("SELECT u FROM UserEntity u WHERE u.email = :login OR u.phoneNumber = :login")
    Optional<UserEntity> findByEmailOrPhoneNumber(@NonNull String login);

    Optional<UserEntity> findByPhoneNumber(String phoneNumber);

}
