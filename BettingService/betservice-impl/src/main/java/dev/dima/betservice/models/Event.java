package dev.dima.betservice.models;

import dev.dima.betservice.models.base.BaseEvent;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "event")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Event extends BaseEvent {

    @Column(name = "is_active")
    private boolean active = true;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<EventOutcome> eventOutcomes = new ArrayList<>();

}
