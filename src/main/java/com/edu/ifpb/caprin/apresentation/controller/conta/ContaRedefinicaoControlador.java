package com.edu.ifpb.caprin.apresentation.controller.conta;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.edu.ifpb.caprin.business.service.impl.conta.ContaRedefinicaoServiceImpl;
import com.edu.ifpb.caprin.business.service.impl.conta.ContaServiceImpl;
import com.edu.ifpb.caprin.model.dto.conta.ContaRedefinicaoEnvioEmail;
import com.edu.ifpb.caprin.model.dto.conta.ContaRedefinicaoRequisicao;
import com.edu.ifpb.caprin.model.entity.conta.Conta;
import com.edu.ifpb.caprin.model.entity.conta.ContaRedefinicao;

import io.swagger.v3.oas.annotations.tags.Tag;

import com.edu.ifpb.caprin.apresentation.compartilhado.resposta.Resposta;
import com.edu.ifpb.caprin.apresentation.endpoints.ContaRedefinicaoEndpoints;

@RestController
@RequestMapping(ContaRedefinicaoEndpoints.PREFIXO)
@AllArgsConstructor
@Tag(name = "ContaRedefinicao", description = "API relacionada a redefinição de senha de conta") 
public class ContaRedefinicaoControlador {

    private final ContaRedefinicaoServiceImpl redefinicaoServico;
    private final ContaServiceImpl contaServico;

    @PreAuthorize("hasAnyAuthority('ADMIN', 'ORGANIZADOR', 'EXPOSITOR')")
    @PostMapping(ContaRedefinicaoEndpoints.REDEFINICAO)
    @ResponseStatus(HttpStatus.OK)
    public Resposta<String> redefinicao(@RequestParam String token, @RequestBody ContaRedefinicaoRequisicao requisicao) {
        Resposta<String> resposta = new Resposta<>();
        ContaRedefinicao redefinicao = redefinicaoServico.findByToken(token);
        Conta conta = redefinicao.getConta();

        redefinicaoServico.validateRedefinition(redefinicao);
        contaServico.updatePassword(conta, requisicao.getNovaSenha(), requisicao.getConfirmarSenha());
        redefinicaoServico.deleteById(redefinicao.getId());

        resposta.setEndpoint(ContaRedefinicaoEndpoints.REDEFINICAO);
        resposta.setConteudo("A senha da conta foi atualizada com sucesso!");
        return resposta;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'ORGANIZADOR', 'EXPOSITOR')")
    @PostMapping(ContaRedefinicaoEndpoints.ENVIAR_REDEFINICAO)
    @ResponseStatus(HttpStatus.OK)
    public Resposta<String> enviarRedefinicao(@RequestBody ContaRedefinicaoEnvioEmail request) {
        Resposta<String> resposta = new Resposta<>();
        redefinicaoServico.enviarEmailRedefinicao(request.getEmail());
        resposta.setEndpoint(ContaRedefinicaoEndpoints.ENVIAR_REDEFINICAO);
        resposta.setConteudo("O email com código para redefinição de senha foi enviado com sucesso!");
        return resposta;
    }

}
