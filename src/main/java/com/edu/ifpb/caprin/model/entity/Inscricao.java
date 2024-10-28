package com.edu.ifpb.caprin.model.entity;

import jakarta.persistence.*;
import java.util.Date;

import com.edu.ifpb.caprin.model.entity.Enum.InscricaoStatus;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Data
@Schema(description = "Representa uma inscrição")
public class Inscricao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único da inscrição", example = "1")
    private Long id;

    @Schema(description = "Data da inscrição", example = "2023-05-01")
    private Date dataInscricao;

    @Schema(description = "Data de cancelamento da inscrição", example = "2023-05-10")
    private Date canceladaEm;

    @Schema(description = "Justificativa do cancelamento", example = "Problemas pessoais")
    private String cancelamentoJustificativa;

    @Enumerated(EnumType.STRING)
    @Schema(description = "Status da inscrição", example = "CONFIRMADA")
    private InscricaoStatus status;

    @ManyToOne
    @JoinColumn(name = "exposicao_id", nullable = false)
    @Schema(description = "Exposição associada à inscrição")
    private Exposicao exposicao;

    @ManyToOne
    @JoinColumn(name = "animal_id", nullable = false)
    @Schema(description = "Animal associado à inscrição")
    private Animal animal;

    @ManyToOne
    @JoinColumn(name = "expositor_id", nullable = false)
    @Schema(description = "Expositor associado à inscrição")
    private Expositor expositor;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pagamento_id")
    @Schema(description = "Pagamento associado à inscrição")
    private Pagamento pagamento;

    @Schema(description = "Verifica se a inscrição foi efetivada", example = "true")
    public boolean isEfetivada() {
        return pagamento != null && pagamento.getDataConfirmacao() != null;
    }
}
