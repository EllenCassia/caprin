package com.edu.ifpb.caprin.model.entity;
import com.edu.ifpb.caprin.model.entity.Enum.Intermediadora;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;


@Entity
@Data
@Table(name = "tb_pagamento")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String idTransacaoIntermediadora;

    @Enumerated(EnumType.STRING)
    private Intermediadora intermediadora; 

    private Date dataConfirmacao;

    private String estado; // Ex: "PENDENTE", "CONFIRMADO", "CANCELADO"
}
