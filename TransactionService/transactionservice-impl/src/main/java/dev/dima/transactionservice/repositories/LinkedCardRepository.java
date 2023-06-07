package dev.dima.transactionservice.repositories;

import dev.dima.transactionservice.models.LinkedCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface LinkedCardRepository extends JpaRepository<LinkedCard, UUID> {

    @Modifying
    @Query("DELETE FROM LinkedCard lc WHERE lc.user.id = :userId AND lc.id = :id")
    void deleteByUserIdAndId(@Param("userId") UUID userId, @Param("id") UUID id);

}
