package dev.dima.betservice.models;

import dev.dima.betservice.dtos.enums.DealType;
import dev.dima.betservice.dtos.enums.ResultType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "deals")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Deal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", columnDefinition = "deal_type")
    private DealType dealType;

    @Column(name = "money")
    private int money;

    @Column(name = "coefficient")
    private double coefficient;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ResultType status;

    @OneToMany(mappedBy = "deal", cascade = CascadeType.ALL)
    private List<Bet> bets = new ArrayList<>();


    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}
