package com.edu.ifpb.caprin.model.dto.token;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
@Builder
public class TokenResposta {

    private String tokenAcesso;
    private Date dataCriacao;
    private Date dataExpiracao;

}
