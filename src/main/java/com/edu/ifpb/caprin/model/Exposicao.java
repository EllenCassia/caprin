package com.edu.ifpb.caprin.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Exposicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private Date dataInicio;

    private Date dataTermino;

    private Date inscricaoInicio;

    private Date inscricaoTermino;

    private Float inscricaoValor;

    private String cidade;

    private String estado;

    private String localizacao;

    private String siteDivulgacao;

    private Integer monitorarNumeroMaximoAnimaisPorExpositor = 30;

    private Integer exigirRGNIdadeMeses = 6;

    private Integer exigirRGDMachoIdadeMeses = 12;

    private Integer exigirRGDFemeaIdadeMeses = 18;

    private Date dataAdmissaoAnimais;

    @OneToMany(mappedBy = "exposicao")
    private List<Campeonato> campeonatos;

    @PrePersist
    @PreUpdate
    public void calcularDataAdmissaoAnimais() {
        if (this.dataInicio != null) {
            this.dataAdmissaoAnimais = this.dataInicio;
        }
    }

    // Getters e Setters
}
