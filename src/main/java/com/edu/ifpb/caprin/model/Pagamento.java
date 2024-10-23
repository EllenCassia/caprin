package com.edu.ifpb.caprin.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String idTransacaoIntermediadora;

    @Enumerated(EnumType.STRING)
    private Intermediadora intermediadora;

    private Date dataConfirmacao;

    private String estado; // Ex: "PENDENTE", "CONFIRMADO", "CANCELADO"

    // Getters e Setters
}
