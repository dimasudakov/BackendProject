package dev.dima.betservice.models;

import dev.dima.betservice.models.base.BaseBet;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "bets")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Bet extends BaseBet {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "deal_id", nullable = false)
    private Deal deal;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "outcome_id", nullable = false)
    private EventOutcome eventOutcome;

}
