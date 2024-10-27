package com.edu.ifpb.caprin.model;

import lombok.Data;
import jakarta.persistence.*;


@Data
@Entity
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
