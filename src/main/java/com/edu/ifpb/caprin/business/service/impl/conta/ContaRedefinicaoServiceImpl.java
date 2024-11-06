package com.edu.ifpb.caprin.business.service.impl.conta;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.edu.ifpb.caprin.business.service.conta.ContaRedefinicaoService;
import com.edu.ifpb.caprin.business.service.exception.NoSuchElementFoundException;
import com.edu.ifpb.caprin.business.service.exception.TokenException;
import com.edu.ifpb.caprin.model.compartilhado.repositorio.DominioRepositorio;
import com.edu.ifpb.caprin.model.entity.conta.Conta;
import com.edu.ifpb.caprin.model.entity.conta.ContaConfirmacao;
import com.edu.ifpb.caprin.model.entity.conta.ContaRedefinicao;
import com.edu.ifpb.caprin.model.repository.conta.ContaRedefinicaoRepository;
import com.edu.ifpb.caprin.model.utils.ConstantesUtils;
import com.edu.ifpb.caprin.model.utils.DataUtils;
import com.edu.ifpb.caprin.model.utils.EmailUtils;
import com.edu.ifpb.caprin.model.utils.UrlUtils;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ContaRedefinicaoServiceImpl implements ContaRedefinicaoService {

    private final ContaRedefinicaoRepository redefinicaoRepositorio;
    private final UrlUtils urlUtils;
    private final EmailUtils emailUtils;
    private final ContaServiceImpl contaServico;

    @Override
    public DominioRepositorio<ContaRedefinicao, Long> getRepositorio() {
        return redefinicaoRepositorio;
    }

    @Override
    public Class<ContaRedefinicao> getDominioClasse() {
        return ContaRedefinicao.class;
    }

    @Override
    public ContaRedefinicao register(Conta conta, String token) {
        var redefinicao = ContaRedefinicao.builder()
                .token(token)
                .dataExpiracao(LocalDateTime.now().plusDays(ConstantesUtils.TEMPO_EXPIRACAO_REDEFINICAO))
                .conta(conta)
                .build();
        return ContaRedefinicaoService.super.register(redefinicao);
    }

    @Override
    public ContaRedefinicao findByToken(String token) {
        Optional<ContaRedefinicao> entidade = redefinicaoRepositorio.findByToken(token);
        return entidade.orElseThrow(() -> new NoSuchElementFoundException(getDominioClasse() + "NÃO ENCONTRADO token=" + token));
    }

    @Override
    public ContaRedefinicao findByConta(Conta conta) {
        Optional<ContaRedefinicao> entidade = redefinicaoRepositorio.findByConta(conta);
        return entidade.orElseThrow(() -> new NoSuchElementFoundException(getDominioClasse() + "NÃO ENCONTRADO conta com email=" + conta.getEmail()));
    }

    @Override
    public void validateRedefinition(ContaRedefinicao redefinicao) {
        String token = redefinicao.getToken();
        if (DataUtils.isDateExpired(redefinicao.getDataExpiracao())) {
            deleteById(redefinicao.getId());
            throw new TokenException(ContaConfirmacao.class + " TOKEN EXPIRADO token=" + token);
        }

    }

    @Override
    public boolean existsByToken(String token) {
        return redefinicaoRepositorio.existsByToken(token);
    }

    @Override
    public void enviarEmailRedefinicao(String emailDestinatario) {
        Conta conta = contaServico.findByEmail(emailDestinatario);
        String token = gerarCodigoVerificacao();
        register(conta, token);
        int tentativas = emailUtils.getTentativasEnvioRedefinicao(conta.getEmail(), token, 1);
        increaseAmountAttempts(conta, tentativas);
        if (tentativas >= 5) {
            contaServico.deleteById(conta.getId());
        }
    }

    private String gerarCodigoVerificacao() {
        String token = null;
        boolean exist = true;
        while (exist) {
            token = urlUtils.gerarCodigoVerificacao();
            exist = existsByToken(token);
        }
        return token;
    }

    private void increaseAmountAttempts(Conta conta, int quantidade) {
        ContaRedefinicao redefinicao = findByConta(conta);
        redefinicao.setTentativas(redefinicao.getTentativas() + quantidade);
        update(redefinicao.getId(), redefinicao);
    }

}