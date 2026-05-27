package com.chamo.chamosports.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="result")
public class ResultEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "teamId")
    private TeamEntity team;

    private Long powerShoot;
    private Long speedShoot;
    private Long effectiveShoot;
    private Long totalShoot;
}
