package com.edu.ifpb.caprin.model;

import jakarta.persistence.*;

@Entity
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo; // EX: "ADMIN", "ORGANIZADOR", "EXPOSITOR"

    @Column(unique = true)
    private String email;

    private String senha;

    @Column(unique = true)
    private String cpf;

    private String telefoneDDD;

    private String telefone;

    private String nome;

    private boolean ativa;

    @ManyToOne
    private Endereco endereco;

    @OneToOne(mappedBy = "conta")
    private Expositor expositor;

    // Validação do tipo de conta
    public void setTipo(String tipo) {
        if (!tipo.equals("ADMIN") && !tipo.equals("ORGANIZADOR") && !tipo.equals("EXPOSITOR")) {
            throw new IllegalArgumentException("Tipo de conta inválido");
        }
        this.tipo = tipo;
    }

    // Getters e Setters
}

