package com.edu.ifpb.caprin.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Campeonato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String regulamento;

    @ManyToOne
    @JoinColumn(name = "exposicao_id")
    private Exposicao exposicao;

    @OneToMany(mappedBy = "campeonato")
    private List<CampeonatoCategoria> categorias;

    // Getters e Setters
}
