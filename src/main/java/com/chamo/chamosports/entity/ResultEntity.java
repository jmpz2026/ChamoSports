package com.chamo.chamosports.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="result")
public class ResultEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "trainingId", nullable = false)
    private TrainingEntity training;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private UserEntity user;

    @Column(nullable = false)
    private Long powerShoot;

    @Column(nullable = false)
    private Long speedShoot;

    @Column(nullable = false)
    private Long effectiveShoot;

    @Column(nullable = false)
    private Long totalShoot;
}