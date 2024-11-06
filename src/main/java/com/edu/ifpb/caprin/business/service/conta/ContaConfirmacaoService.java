package com.edu.ifpb.caprin.business.service.conta;

import com.edu.ifpb.caprin.business.service.exception.NoSuchElementFoundException;
import com.edu.ifpb.caprin.business.service.exception.TokenException;
import com.edu.ifpb.caprin.model.entity.conta.Conta;
import com.edu.ifpb.caprin.model.entity.conta.ContaConfirmacao;
import com.edu.ifpb.caprin.model.repository.conta.ContaConfirmacaoRepository;
import com.edu.ifpb.caprin.model.utils.ConstantesUtils;
import com.edu.ifpb.caprin.model.utils.DataUtils;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ContaConfirmacaoService{

    @Autowired
    private final ContaConfirmacaoRepository contaConfirmacaoRepositorio;

    public void validateConfirmation(ContaConfirmacao confirmacao) {
        String token = confirmacao.getToken();

        if (DataUtils.isDateExpired(confirmacao.getDataExpiracao()))
            throw new TokenException(ContaConfirmacao.class + " TOKEN EXPIRADO token=" + token);
    }

    public ContaConfirmacao register(Conta conta , String token) {
        var confirmacao = ContaConfirmacao.builder()
                .token(token)
                .dataExpiracao(LocalDateTime.now().plusDays(ConstantesUtils.TEMPO_EXPIRACAO_CONFIRMACAO))
                .conta(conta)
                .build();
        return contaConfirmacaoRepositorio.save(confirmacao);
    }

    public void deleteById(long idConfirmacao) {
        contaConfirmacaoRepositorio.deleteById(idConfirmacao);
    }

    public void increaseAmountAttempts(Conta conta, int quantidade) {
        ContaConfirmacao confirmacao = findByConta(conta);
        confirmacao.setTentativas(confirmacao.getTentativas()+quantidade);
        update(confirmacao.getId(), confirmacao);
    }

    public ContaConfirmacao update(Long id, ContaConfirmacao confirmacao) {
        confirmacao.setId(id);
        return contaConfirmacaoRepositorio.save(confirmacao);
    }

    public ContaConfirmacao findByToken(String token) {
        Optional<ContaConfirmacao> entity = contaConfirmacaoRepositorio.findByToken(token);
        return entity.orElseThrow(() -> new NoSuchElementFoundException(ContaConfirmacao.class + " NÃO ENCONTRADO token=" + token));
    }

    public ContaConfirmacao findByConta(Conta conta) {
        Optional<ContaConfirmacao> entity = contaConfirmacaoRepositorio.findByConta(conta);
        return entity.orElseThrow(() -> new NoSuchElementFoundException(ContaConfirmacao.class + " NÃO ENCONTRADO conta=" + conta));
    }

}