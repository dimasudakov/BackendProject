package dev.dima.betservice.models.base;

import dev.dima.betservice.dtos.enums.DealType;
import dev.dima.betservice.dtos.enums.ResultType;
import dev.dima.betservice.models.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public abstract class BaseDeal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    protected UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    protected User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", columnDefinition = "deal_type")
    protected DealType dealType;

    @Column(name = "money")
    protected int money;

    @Column(name = "coefficient")
    protected double coefficient;

    @Column(name = "created_at", updatable = false)
    protected LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    protected ResultType status;
}
