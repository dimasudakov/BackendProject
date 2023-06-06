package dev.dima.betservice.models.archive;


import dev.dima.betservice.models.Bet;
import dev.dima.betservice.models.Deal;
import dev.dima.betservice.models.base.BaseDeal;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "deals_archive")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ArchivedDeal extends BaseDeal {

    @OneToMany(mappedBy = "archivedDeal", cascade = CascadeType.ALL)
    private List<ArchivedBet> bets = new ArrayList<>();

    public ArchivedDeal(Deal deal) {
        super();
        BeanUtils.copyProperties(deal, this);

        for(Bet bet : deal.getBets()) {
            this.bets.add(new ArchivedBet(bet, this));
        }
    }
}
