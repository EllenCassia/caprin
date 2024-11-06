package com.edu.ifpb.caprin.business.service.conta;

import com.edu.ifpb.caprin.business.service.compartilhado.servico.DominioServico;
import com.edu.ifpb.caprin.model.entity.conta.Conta;
import com.edu.ifpb.caprin.model.entity.conta.ContaRedefinicao;

public interface ContaRedefinicaoService extends DominioServico<ContaRedefinicao, Long> {

    ContaRedefinicao findByToken(String token);

    ContaRedefinicao findByConta(Conta conta);

    void validateRedefinition(ContaRedefinicao redefinicao);

    boolean existsByToken(String token);

    ContaRedefinicao register(Conta conta, String token);

    void enviarEmailRedefinicao(String emailDestinatario);
    
}