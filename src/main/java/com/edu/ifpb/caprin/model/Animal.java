package com.edu.ifpb.caprin.model;

import lombok.Data;

import jakarta.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "animal")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @Column(name = "id_siscapri", nullable = false)
    private Long idSiscapri;

    @Column(name = "data_extracao_siscapri")
    @Temporal(TemporalType.DATE)
    private Date dataExtracaoSiscapri;

    @Column(name = "data_nascimento")
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;

    @Column(name = "sexo", nullable = false)
    private char sexo;

    @Column(name = "dna", nullable = false)
    private boolean DNA;

    @Column(name = "tod", nullable = false)
    private String TOD;

    @Column(name = "toe", nullable = false)
    private String TOE;

    // Campo calculado que concatena TOD e TOE
    @Transient
    private String registro;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "raca", nullable = false)
    private String raca;

    @Column(name = "pelagem", nullable = false)
    private String pelagem;

    @Column(name = "criador", nullable = false)
    private String criador;

    @Column(name = "proprietario", nullable = false)
    private String proprietario;

    @Column(name = "afixo")
    private String afixo;

    @Column(name = "situacao")
    private String situacao;

    @Column(name = "inconsistencia")
    private String inconsistencia;

    // Método para calcular o registro (TOD + TOE)
    public String getRegistro() {
        return this.TOD + this.TOE;
    }
}
