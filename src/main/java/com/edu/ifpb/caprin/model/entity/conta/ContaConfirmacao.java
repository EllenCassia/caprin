package com.edu.ifpb.caprin.model.entity.conta;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;


// @Data
// @Entity
// @Table(name = "TE_CONTA_CONFIRMACAO", schema = "CONTA")
// public class ContaConfirmacao {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @Column(nullable = false, unique = true)
//     private String token;

//     private LocalDateTime dataExpiracao;

//     private int tentativas;

//     @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
//     @JoinColumn(name = "ID_CONTA", referencedColumnName = "ID")
//     private Conta conta;
// }
