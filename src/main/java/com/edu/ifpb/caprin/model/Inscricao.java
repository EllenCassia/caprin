package com.edu.ifpb.caprin.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Inscricao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date dataInscricao;

    private Date canceladaEm;

    private String cancelamentoJustificativa;

    @OneToOne
    private Pagamento pagamento;

    @ManyToOne
    private Exposicao exposicao;

    @ManyToOne
    private CampeonatoCategoria campeonatoCategoria;

    @ManyToOne
    private Expositor expositor;

    @ManyToOne
    private Animal animal;

    // Método para verificar se a inscrição foi efetivada
    //public boolean isEfetivada() {
    //    return pagamento != null && pagamento.getDataConfirmacao() != null;
    //}

    // Getters e Setters
}

