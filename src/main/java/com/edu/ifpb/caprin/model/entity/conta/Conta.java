package com.edu.ifpb.caprin.model.entity.conta;

import com.edu.ifpb.caprin.model.compartilhado.modelo.DominioModelo;
import com.edu.ifpb.caprin.model.dto.conta.ContaRequisicao;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Table(name = "TE_CONTA")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Conta extends DominioModelo<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false, name = "TELEFONE_DDD")
    private short telefoneDDD;

    @Column(nullable = false)
    private long telefone;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean ativa;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(length = 3, name = "CONTA_TIPO")
    private String contaTipo = "000";

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "ID_ENDERECO", referencedColumnName = "ID")
    private Endereco endereco;

    public Conta(ContaRequisicao requisicao) {
        this.email = requisicao.getEmail();
        this.senha = null;
        this.cpf = requisicao.getCpf();
        this.telefoneDDD = requisicao.getTelefoneDDD();
        this.telefone = requisicao.getTelefone();
        this.ativa = true;
        this.contaTipo = null;
    }
}