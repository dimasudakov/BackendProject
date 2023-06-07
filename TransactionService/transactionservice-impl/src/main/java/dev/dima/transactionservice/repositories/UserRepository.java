package dev.dima.transactionservice.repositories;


import dev.dima.transactionservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    @Query("SELECT u.balance FROM User u WHERE u.id = :userId")
    Integer findBalanceById(@Param("userId") UUID userId);
}
