package com.edu.ifpb.caprin.model.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Schema(description = "Representa uma exposição")
public class Exposicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Nome da exposição", example = "Exposição Agropecuária")
    private String nome;

    @Schema(description = "Data de início da exposição", example = "2023-05-01")
    private Date dataInicio;

    @Schema(description = "Data de término da exposição", example = "2023-05-10")
    private Date dataTermino;

    @Schema(description = "Data de início das inscrições", example = "2023-04-01")
    private Date inscricaoInicio;

    @Schema(description = "Data de término das inscrições", example = "2023-04-20")
    private Date inscricaoTermino;

    @Schema(description = "Valor da inscrição", example = "150.00")
    private Float inscricaoValor;

    @Schema(description = "Cidade onde a exposição será realizada", example = "São Paulo")
    private String cidade;

    @Schema(description = "Estado onde a exposição será realizada", example = "SP")
    private String estado;

    @Schema(description = "Localização da exposição", example = "Parque de Exposições")
    private String localizacao;

    @Schema(description = "Site de divulgação da exposição", example = "www.exposicaoagro.com.br")
    private String siteDivulgacao;

    @Schema(description = "Número máximo de animais por expositor a ser monitorado", example = "30")
    private Integer monitorarNumeroMaximoAnimaisPorExpositor = 30;

    @Schema(description = "Idade mínima em meses para exigir RGN", example = "6")
    private Integer exigirRGNIdadeMeses = 6;

    @Schema(description = "Idade mínima em meses para exigir RGD de machos", example = "12")
    private Integer exigirRGDMachoIdadeMeses = 12;

    @Schema(description = "Idade mínima em meses para exigir RGD de fêmeas", example = "18")
    private Integer exigirRGDFemeaIdadeMeses = 18;

    @Transient
    @Schema(description = "Data de admissão dos animais", example = "2023-04-30")
    private Date dataAdmissaoAnimais;

    @ManyToOne
    @JoinColumn(name = "campeonato_id", nullable = false)
    @Schema(description = "Campeonato associado à exposição")
    private Campeonato campeonato;

    @OneToMany(mappedBy = "exposicao", cascade = CascadeType.ALL)
    private List<Inscricao> inscricoes;
}