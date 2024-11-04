package com.edu.ifpb.caprin.model.entity.conta;

import com.edu.ifpb.caprin.model.compartilhado.modelo.DominioModelo;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "TE_ENDERECO")
public class Endereco extends DominioModelo<Long>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String cep;

    @Column(nullable = false)
    private String logradouro;

    @Column(nullable = false)
    private String numero;

    private String complemento;

    @Column(nullable = false)
    private String cidade;

    @Column(nullable = false)
    private String estado;



}
