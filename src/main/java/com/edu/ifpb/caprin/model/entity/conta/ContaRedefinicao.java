package com.edu.ifpb.caprin.model.entity.conta;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import com.edu.ifpb.caprin.model.compartilhado.modelo.DominioModelo;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Table(name = "TE_CONTA_REDEFINICAO")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContaRedefinicao extends DominioModelo<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String token;

    private LocalDateTime dataExpiracao;

    private int tentativas;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_CONTA", referencedColumnName = "ID")
    private Conta conta;

}
