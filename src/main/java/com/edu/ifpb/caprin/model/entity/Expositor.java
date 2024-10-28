package com.edu.ifpb.caprin.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
@Table(name = "TB_EXPOSITOR")
public class Expositor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String tod;

    private String nome;

    private String estado;

    private String cidade;

    @OneToOne
    @JoinColumn(name = "conta_id")
    private Conta conta;

    @OneToMany(mappedBy = "expositor", cascade = CascadeType.ALL)
    private List<Inscricao> inscricoes;

}
