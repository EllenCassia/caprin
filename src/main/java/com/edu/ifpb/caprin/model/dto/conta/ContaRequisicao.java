package com.edu.ifpb.caprin.model.dto.conta;

import java.util.Set;
import java.io.Serializable;

import com.edu.ifpb.caprin.model.entity.Enum.ContaTipo;
import com.edu.ifpb.caprin.model.entity.conta.Endereco;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;

import lombok.Data;

@Data
public class ContaRequisicao {

    private static final long serialVersionUID = 1L;

    private Long id;

    @Email
    private String email;

    private String senha;

    private String cpf;

    @Digits(integer = 2, fraction = 0)
    private short telefoneDDD;

    @Digits(integer = 10, fraction = 0)
    private long telefone;

    private boolean ativa;

    private Set<ContaTipo> contaTipo;

    private Endereco endereco;
    
}
