package com.edu.ifpb.caprin.model.repository.conta;

import com.edu.ifpb.caprin.model.compartilhado.repositorio.DominioRepositorio;
import com.edu.ifpb.caprin.model.entity.conta.Conta;
import com.edu.ifpb.caprin.model.entity.conta.ContaRedefinicao;

import java.util.Optional;

public interface ContaRedefinicaoRepository extends DominioRepositorio<ContaRedefinicao, Long> {
    Optional<ContaRedefinicao> findByToken(String token);
    Optional<ContaRedefinicao> findByConta(Conta conta);

    boolean existsByToken(String token);
}