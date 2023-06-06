package dev.dima.betservice.models;

import dev.dima.betservice.models.base.BaseDeal;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "deals")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Deal extends BaseDeal {



    @OneToMany(mappedBy = "deal", cascade = CascadeType.ALL)
    private List<Bet> bets = new ArrayList<>();


    @PrePersist
    public void prePersist() {
        if(this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
    }
}
