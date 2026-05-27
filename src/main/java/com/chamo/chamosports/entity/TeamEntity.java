package com.chamo.chamosports.entity;

import jakarta.persistence.*;

@Entity
@Table(name="team")
public class TeamEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 20, unique = true)
    private String name;
}
