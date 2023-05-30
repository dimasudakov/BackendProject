package dev.dima.betservice.models;

import dev.dima.betservice.dtos.enums.ResultType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.util.UUID;

@Entity
@Table(name = "event_outcomes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EventOutcome {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "outcome_type_id")
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    private OutcomeType outcomeType;

    @Column(name = "coefficient")
    private double coefficient;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ResultType status;

}
