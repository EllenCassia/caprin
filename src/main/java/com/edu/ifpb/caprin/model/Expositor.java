package com.edu.ifpb.caprin.model;

import jakarta.persistence.*;

@Entity
public class Expositor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String tod;

    private String nome;

    private String estado;

    private String cidade;

    @OneToOne
    @JoinColumn(name = "conta_id")
    private Conta conta;

    // Getters e Setters
}
