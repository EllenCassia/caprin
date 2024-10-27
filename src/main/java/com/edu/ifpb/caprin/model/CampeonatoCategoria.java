package com.edu.ifpb.caprin.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;

@Entity
@Data
@Table(name = "TB_CAMPEONATO_CATEGORIA")
@Schema(description = "Representa uma categoria de um campeonato")
public class CampeonatoCategoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único da categoria", example = "1")
    private Long id;

    @Schema(description = "Nome da categoria", example = "Categoria Juvenil")
    private String nome;
    
    @Schema(description = "Idade mínima em meses", example = "12")
    private Integer idadeMinimaMeses;
    
    @Schema(description = "Idade máxima em meses", example = "24")
    private Integer idadeMaximaMeses;
    
    @Schema(description = "Indica se a dentição de leite é exigida", example = "true")
    private boolean denticaoLeite;
    
    @Schema(description = "Quantidade limite de dentes de leite", example = "8")
    private Integer denticaoQuantidadeLimite;
    
    @Schema(description = "Número mínimo de expositores por raça a ser monitorado", example = "3")
    private Integer monitorarNumeroMinimoExpositoresPorRaca = 3;
    
    @Schema(description = "Número mínimo de animais inscritos por raça a ser monitorado", example = "25")
    private Integer monitorarNumeroMinimoAnimaisInscritosPorRaca = 25;
    
    @Schema(description = "Número máximo de animais por expositor", example = "2")
    private Integer exigirNumeroMaximoAnimaisPorExpositor = 2;

    @ManyToOne
    @JoinColumn(name = "campeonato_id")
    @Schema(description = "Campeonato associado à categoria")
    private Campeonato campeonato;
}
