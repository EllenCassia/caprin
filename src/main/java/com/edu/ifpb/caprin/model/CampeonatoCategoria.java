package com.edu.ifpb.caprin.model;

import jakarta.persistence.*;

@Entity
public class CampeonatoCategoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private Integer idadeMinimaMeses;

    private Integer idadeMaximaMeses;

    private boolean denticaoLeite;

    private Integer denticaoQuantidadeLimite;

    private Integer monitorarNumeroMinimoExpositoresPorRaca = 3;

    private Integer monitorarNumeroMinimoAnimaisInscritosPorRaca = 25;

    private Integer exigirNumeroMaximoAnimaisPorExpositor = 2;

    @ManyToOne
    @JoinColumn(name = "campeonato_id")
    private Campeonato campeonato;

    // Getters e Setters
}
