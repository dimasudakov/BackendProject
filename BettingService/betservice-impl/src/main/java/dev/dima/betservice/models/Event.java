package dev.dima.betservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "event")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "discipline_id")
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    private Discipline discipline;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "competition")
    private String competitionName;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "is_active")
    private boolean active = true;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "competitor_1_id", nullable = false)
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    private Competitor competitor1;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "competitor_2_id", nullable = false)
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    private Competitor competitor2;

}
