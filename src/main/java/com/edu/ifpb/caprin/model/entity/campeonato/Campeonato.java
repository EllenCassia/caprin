package com.edu.ifpb.caprin.model.entity.campeonato;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

import com.edu.ifpb.caprin.model.entity.Exposicao;  

@Entity
@Data
@Table(name = "TB_CAMPEONATO")
@Schema(description = "Representa um campeonato")
public class Campeonato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Schema(description = "Nome do campeonato", example = "Campeonato Brasileiro")
    private String nome;

    @Column(nullable = false)
    @Schema(description = "Regulamento do campeonato", example = "Regulamento oficial do campeonato")
    private String regulamento;
    
    @OneToMany(mappedBy = "campeonato", cascade = CascadeType.ALL)
    @Schema(description = "Categorias associadas ao campeonato")
    private List<CampeonatoCategoria> categorias;

    @OneToMany(mappedBy = "campeonato", cascade = CascadeType.ALL)
    private List<Exposicao> exposicoes;
    
}