package dev.dima.betservice.models.archive;


import dev.dima.betservice.models.Bet;
import dev.dima.betservice.models.OutcomeType;
import dev.dima.betservice.models.base.BaseBet;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "bets_archive")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ArchivedBet extends BaseBet {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "deal_archive_id", nullable = false)
    private ArchivedDeal archivedDeal;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "event_archive_id", nullable = false)
    @Cascade(CascadeType.ALL)
    private ArchivedEvent event;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "outcome_id", nullable = false)
    @Cascade(CascadeType.PERSIST)
    private OutcomeType outcomeType;

    public ArchivedBet(Bet bet, ArchivedDeal archivedDeal) {
        super(bet.getId(), bet.getCoefficient());
        this.archivedDeal = archivedDeal;
        this.event = new ArchivedEvent(bet.getEventOutcome().getEvent());
        this.outcomeType = bet.getEventOutcome().getOutcomeType();
    }
}
