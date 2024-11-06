package com.edu.ifpb.caprin.model.repository.conta;

import com.edu.ifpb.caprin.model.entity.conta.Conta;
import com.edu.ifpb.caprin.model.entity.conta.ContaConfirmacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContaConfirmacaoRepository extends JpaRepository<ContaConfirmacao, Long> {
    Optional<ContaConfirmacao> findByToken(String token);
    Optional<ContaConfirmacao> findByConta(Conta conta);
}
