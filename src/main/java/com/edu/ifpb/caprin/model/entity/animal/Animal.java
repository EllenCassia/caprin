package com.edu.ifpb.caprin.model.entity.animal;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import com.edu.ifpb.caprin.model.compartilhado.modelo.DominioModelo;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "TE_ANIMAL")
@AllArgsConstructor
@NoArgsConstructor
public class Animal extends DominioModelo<Long> {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idExterno;

    @Type(JsonType.class)
    @Column(columnDefinition = "json")
    private AnimalParentesco pai;

    @Type(JsonType.class)
    @Column(columnDefinition = "json")
    private AnimalParentesco mae;

    private String registro;
    private String nome;
    private String situacao;
    private boolean DNA;
    private String TOD;
    private String TOE;
    private String criador;
    private String proprietario;
    private String afixo;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNascimento;
    
    private char sexo;
    private String categoria;
    private String raca;

    @Override
    public Long getId() {
        return this.id;
    }

    public void setRegistro(String TOD, String TOE) {
        this.registro = TOD + TOE;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Animal animal = (Animal) o;

        if (DNA != animal.DNA) return false;
        if (sexo != animal.sexo) return false;
        if (!Objects.equals(registro, animal.registro)) return false;
        if (!Objects.equals(nome, animal.nome)) return false;
        if (!Objects.equals(TOD, animal.TOD)) return false;
        if (!Objects.equals(TOE, animal.TOE)) return false;
        if (!Objects.equals(criador, animal.criador)) return false;
        if (!Objects.equals(categoria, animal.categoria)) return false;
        if (!Objects.equals(dataNascimento, animal.dataNascimento)) return false;
        return Objects.equals(raca, animal.raca);
    }

}
