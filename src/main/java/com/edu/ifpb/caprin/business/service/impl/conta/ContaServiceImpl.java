package com.edu.ifpb.caprin.business.service.impl.conta;


import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edu.ifpb.caprin.business.service.conta.ContaService;
import com.edu.ifpb.caprin.business.service.conta.EnderecoService;
import com.edu.ifpb.caprin.business.service.exception.CpfAlreadyExistsException;
import com.edu.ifpb.caprin.business.service.exception.EmailAlreadyExistsException;
import com.edu.ifpb.caprin.business.service.exception.NoSuchElementFoundException;
import com.edu.ifpb.caprin.business.service.exception.PasswordNotMatchingException;
import com.edu.ifpb.caprin.model.compartilhado.repositorio.DominioRepositorio;
import com.edu.ifpb.caprin.model.entity.conta.Conta;
import com.edu.ifpb.caprin.model.entity.conta.Endereco;
import com.edu.ifpb.caprin.model.repository.conta.ContaRepository;
import com.edu.ifpb.caprin.model.utils.EmailUtils;
import com.edu.ifpb.caprin.model.utils.UrlUtils;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ContaServiceImpl implements ContaService {

    private final ContaRepository contaRepositorio;

    private final PasswordEncoder passwordEncoder;

    private final EmailUtils emailUtils;

    private final UrlUtils urlUtils;

    // private final ContaConfirmacaoServico confirmacaoServico;

    private final EnderecoService enderecoServico;

    @Override
    public DominioRepositorio<Conta, Long> getRepositorio() {
        return contaRepositorio;
    }
    public Class<Conta> getDominioClasse() {
        return Conta.class;
    }

    public Conta activateAccount(Conta conta) {
        conta.setAtiva(true);
        return contaRepositorio.save(conta);
    }

    public Conta updatePassword(Conta conta, String novaSenha, String confirmacaoSenha) {
        verificarSenhaCorresponde(novaSenha, confirmacaoSenha);
        String senhaCriptografada = passwordEncoder.encode(novaSenha);
        conta.setSenha(senhaCriptografada);
        return contaRepositorio.save(conta);
    }

    // public Conta update(Long id, ContaRequisicao requisicao) {
    //     verificarEmailExiste(requisicao.getEmail());
    //     verificarCpfExiste(requisicao.getCpf());

    //     Conta contaAtualizada = new Conta(requisicao);
    //     return ContaServico.super.update(id, contaAtualizada);
    // }

    @Override
    @Transactional
    public Conta register(Conta conta) {
        verificarEmailExiste(conta.getEmail());
        verificarCpfExiste(conta.getCpf());

        Endereco endereco = conta.getEndereco();
        enderecoServico.create(endereco);
        String senhaCriptografada = passwordEncoder.encode(conta.getSenha());
        conta.setSenha(senhaCriptografada);
        conta.setEndereco(endereco);
        Conta contaNova = contaRepositorio.save(conta);
        // enviarEmailConfirmacao(contaNova);
        return contaNova;
    }

    public Conta findByEmail(String email) {
        Optional<Conta> entidade = contaRepositorio.findByEmail(email);
        return entidade.orElseThrow(() -> new NoSuchElementFoundException(getDominioClasse() + " NÃO ENCONTRADO e-mail=" + email));
    }

    // private void enviarEmailConfirmacao(Conta conta) {
    //     String token = gerarTokenConfirmacao();
    //     String url = urlUtils.gerarUrlAtivacaoConta(token);
    //     salvarConfirmacao(conta, token);

    //     int tentativas = emailUtils.getTentativasEnvioAtivacao(conta.getEmail(), url, 1);
    //     confirmacaoServico.increaseAmountAttempts(conta, tentativas);
    //     if (tentativas >= 5) {
    //         deleteById(conta.getId());
    //     }
    // }

    private String gerarTokenConfirmacao() {
        return urlUtils.gerarToken();
    }

    // private void salvarConfirmacao(Conta conta, String token) {
    //     confirmacaoServico.register(conta, token);
    // }

    private void verificarSenhaCorresponde(String senha, String confirmarSenha) {
        if (!senha.equals(confirmarSenha))
            throw new PasswordNotMatchingException(getDominioClasse() + " SENHA E CONFIRMAÇÃO DE SENHA DEVEM SER IGUAIS");
    }

    private void verificarEmailExiste(String email) {
        contaRepositorio.findByEmail(email).ifPresent(conta -> {
            throw new EmailAlreadyExistsException(getDominioClasse() + " CONTA JÁ CADASTRADA COM e-mail=" + email);
        });
    }

    private void verificarCpfExiste(String cpf) {
        contaRepositorio.findByCpf(cpf).ifPresent(conta -> {
            throw new CpfAlreadyExistsException(getDominioClasse() + " CONTA JÁ CADASTRADA COM cpf=" + cpf);
        });
    }



}