package com.edu.ifpb.caprin.apresentation.controller.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Resposta <R> implements Serializable {

    private static final long serialVersionUID = 1L;
    private String endpoint;
    private R conteudo;

    private List<String> erros;


    Resposta(R conteudo) {
        this.conteudo = conteudo;
    }

    public String getEndpoint() {
        return endpoint;
    }
    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }
    public R getConteudo() {
        return conteudo;
    }
    public void setConteudo(R conteudo) {
        this.conteudo = conteudo;
    }

    public List<String> getErros() {
        return erros;
    }
    public void setErros(List<String> erros) {
        this.erros = erros;
    }

    @Override
    public String toString() {
        return "Resposta{" +
                "endpoint='" + endpoint + '\'' +
                ", conteudo=" + conteudo +
                ", erros=" + erros +
                '}';
    }
}

