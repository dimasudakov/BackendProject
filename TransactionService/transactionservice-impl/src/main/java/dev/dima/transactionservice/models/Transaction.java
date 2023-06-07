package dev.dima.transactionservice.models;

import dev.dima.transactionservice.dtos.enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "transaction_history")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "uuid", updatable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "transaction_amount")
    private int transactionAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", columnDefinition = "transaction_type")
    private TransactionType transactionType;
}
