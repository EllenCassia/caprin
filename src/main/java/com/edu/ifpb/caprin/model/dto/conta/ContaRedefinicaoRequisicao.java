package com.edu.ifpb.caprin.model.dto.conta;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContaRedefinicaoRequisicao implements Serializable {

    private String novaSenha;
    private String confirmarSenha;

}