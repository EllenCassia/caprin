package com.edu.ifpb.caprin.model.dto.animal;

import com.edu.ifpb.caprin.model.entity.animal.AnimalParentesco;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnimalRequisicao implements Serializable {

    private Long id;
    private Long idExterno;
//    private AnimalParentesco pai;
//    private AnimalParentesco mae;
    private String registro;
    private String nome;
    private String situacao;
    private boolean DNA;
    private String TOD;
    private String TOE;
    private String criador;
    private String proprietario;
    private String afixo;
    private LocalDate dataNascimento;
    private char sexo;
    private String categoria;
    private String raca;

}