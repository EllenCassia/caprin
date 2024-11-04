package com.edu.ifpb.caprin.business.service.conta;

import org.springframework.stereotype.Service;

import com.edu.ifpb.caprin.business.service.compartilhado.servico.DominioServico;
import com.edu.ifpb.caprin.model.dto.conta.ContaRequisicao;
import com.edu.ifpb.caprin.model.entity.conta.Conta;

@Service
public interface ContaService extends DominioServico<Conta, Long>{

    public abstract Conta register(Conta conta);

    public abstract Conta activateAccount(Conta conta);

    public abstract Conta updatePassword(Conta conta, String novaSenha, String confirmacaoSenha);

    // public abstract Conta update(Long id, ContaRequisicao conta);

    public abstract Conta findByEmail(String email);

}

