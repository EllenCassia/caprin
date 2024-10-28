package com.edu.ifpb.caprin.model.entity;

import lombok.Data;
import jakarta.persistence.*;


@Data
@Entity
@Table(name = "TB_ANIMAL_PARENTESCO")
public class AnimalParentesco {

    @Id
    private String registro;

    @ManyToOne
    @JoinColumn(name = "parentesco_id")
    private Parentesco parentesco;

    private String nome;

    @ManyToOne
    @JoinColumn(name = "pai_id", nullable = true)
    private Animal pai;

    @ManyToOne
    @JoinColumn(name = "mae_id", nullable = true)
    private Animal mae;
}
