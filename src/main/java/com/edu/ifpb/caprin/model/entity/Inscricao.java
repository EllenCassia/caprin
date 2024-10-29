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
    private Long id;

    private Date dataInscricao;

    private Date canceladaEm;

    private String cancelamentoJustificativa;

    @Enumerated(EnumType.STRING)
    private InscricaoStatus status;

    @ManyToOne
    @JoinColumn(name = "exposicao_id", nullable = false)
    private Exposicao exposicao;

    // @ManyToOne
    // @JoinColumn(name = "animal_id", nullable = false)
    // private Animal animal;

    @ManyToOne
    @JoinColumn(name = "expositor_id", nullable = false)
    private Expositor expositor;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pagamento_id")
    private Pagamento pagamento;

    public boolean isEfetivada() {
        return pagamento != null && pagamento.getDataConfirmacao() != null;
    }
}
