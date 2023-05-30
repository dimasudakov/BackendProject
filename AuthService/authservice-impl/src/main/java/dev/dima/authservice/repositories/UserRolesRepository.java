package dev.dima.authservice.repositories;

import dev.dima.authservice.models.UserEntity;
import dev.dima.authservice.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRolesRepository extends JpaRepository<UserRole, UUID> {

    List<UserRole> findByUser(UserEntity user);

}