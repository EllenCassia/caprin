package com.edu.ifpb.caprin.model;

import jakarta.persistence.*;

@Entity
@Table(name = "animal_parentesco")
public class AnimalParentesco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  

    @Column(name = "registro", nullable = false, unique = true)
    private String registro; 

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "parentesco_id", referencedColumnName = "id")  // Chave estrangeira
//    private Parentesco parentesco;  // Relacionamento com a entidade Parentesco

    @Column(name = "nome", nullable = false)
    private String nome;

    // Construtor padrão
    public AnimalParentesco() {
    }

    // Construtor completo
    public AnimalParentesco(String registro, Parentesco parentesco, String nome) {
        this.registro = registro;
        //this.parentesco = parentesco;
        this.nome = nome;
    }
}
