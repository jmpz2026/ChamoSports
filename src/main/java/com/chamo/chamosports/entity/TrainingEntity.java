package com.chamo.chamosports.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="training")
public class TrainingEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "teamId", nullable = false)
    private TeamEntity team;

    @Column(nullable = false)
    private LocalDateTime date;

    @OneToMany(mappedBy = "training", cascade = CascadeType.ALL)
    private List<ResultEntity> results;
}