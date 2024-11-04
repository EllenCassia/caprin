package com.edu.ifpb.caprin.model.dto.login;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginRequisicao {

    private String email;

    private String senha;

}
