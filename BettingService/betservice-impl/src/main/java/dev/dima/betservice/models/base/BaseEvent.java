package dev.dima.betservice.models.base;


import dev.dima.betservice.models.Competitor;
import dev.dima.betservice.models.Discipline;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BaseEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    protected UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "discipline_id")
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    protected Discipline discipline;

    @Column(name = "country")
    protected String country;

    @Column(name = "city")
    protected String city;

    @Column(name = "competition")
    protected String competitionName;

    @Column(name = "start_date")
    protected LocalDateTime startDate;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "competitor_1_id", nullable = false)
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    protected Competitor competitor1;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "competitor_2_id", nullable = false)
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    protected Competitor competitor2;

}
