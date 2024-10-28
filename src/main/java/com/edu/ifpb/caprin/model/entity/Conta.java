package com.edu.ifpb.caprin.model.entity;

import com.edu.ifpb.caprin.model.entity.Enum.ContaTipo;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "TB_CONTA")
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated
    private ContaTipo tipo; // ADMIN, ORGANIZADOR, EXPOSITOR

    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(unique = true)
    private String cpf;

    @Column(unique = true)
    private String telefoneDDD;

    @Column(unique = true)
    private String telefone;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private boolean ativa;

    @OneToOne
    @JoinColumn(name = "endereco_id") 
    private Endereco endereco;

    @OneToOne(mappedBy = "conta")
    private Expositor expositor;

}

