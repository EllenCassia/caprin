package com.edu.ifpb.caprin.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private Long idSiscapri;

    private Date dataExtracaoSiscapri;

    private char sexo;

    private boolean dna;

    private String tod;

    private String toe;

    private String registro; // Calculado: TOD + TOE

    private String nome;

    private String raca;

    private String pelagem;

    private String criador;

    private String proprietario;

    private String afixo;

    private String situacao;

    private String inconsistencia;

    // Construtor para inicializar o registro
    public Animal() {
        this.registro = this.tod + this.toe;
    }

    // Getters e Setters
}
