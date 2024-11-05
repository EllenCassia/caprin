package com.edu.ifpb.caprin.model.entity.animal;

import lombok.Data;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.edu.ifpb.caprin.model.compartilhado.modelo.DominioModelo;


@Data
@Entity
@Table(name = "TB_ANIMAL")

public class Animal extends DominioModelo<Long>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idExterno;

    @Column(name = "id_siscapri", nullable = false)
    private Long idSiscapri;

    @Column(name = "data_extracao_siscapri")
    @Temporal(TemporalType.DATE)
    private Date dataExtracaoSiscapri;

    @Column(name = "data_nascimento")
    @Temporal(TemporalType.DATE)
    private LocalDate dataNascimento;

    @Column(name = "sexo", nullable = false)
    private char sexo;

    @Column(name = "dna", nullable = false)
    private boolean DNA;

    @Column(name = "tod", nullable = false)
    private String TOD;

    @Column(name = "toe", nullable = false)
    private String TOE;

    // Campo calculado que concatena TOD e TOE
    @Transient
    private String registro;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "raca", nullable = false)
    private String raca;

    @Column(name = "pelagem", nullable = false)
    private String pelagem;

    @Column(name = "criador", nullable = false)
    private String criador;

    @Column(name = "proprietario", nullable = false)
    private String proprietario;

    @Column(name = "afixo")
    private String afixo;

    @Column(name = "situacao")
    private String situacao;

    @Column(name = "inconsistencia")
    private String inconsistencia;

    // Relacionamento com a entidade Parentesco
    
//    @OneToMany(mappedBy = "pai")
//    private List<AnimalParentesco> filhospai;
//
//    @OneToMany(mappedBy = "mae")
//    private List<AnimalParentesco> filhosmae;

    // @OneToMany(mappedBy = "animal", cascade = CascadeType.ALL)
    // private List<Inscricao> inscricoes;

    // private AnimalParentesco pai;

    // private AnimalParentesco mae;
    
    // Método para calcular o registro (TOD + TOE)
    public String getRegistro() {
        return this.TOD + this.TOE;
    }
}
