package com.edu.ifpb.caprin.apresentation.controller.conta; 


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edu.ifpb.caprin.apresentation.compartilhado.resposta.Resposta;
import com.edu.ifpb.caprin.apresentation.endpoints.ContaConfirmacaoEndpoints;
import com.edu.ifpb.caprin.business.service.conta.ContaConfirmacaoService;
import com.edu.ifpb.caprin.business.service.impl.conta.ContaServiceImpl;
import com.edu.ifpb.caprin.model.entity.conta.Conta;
import com.edu.ifpb.caprin.model.entity.conta.ContaConfirmacao;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(ContaConfirmacaoEndpoints.PREFIXO)
@AllArgsConstructor
@Tag(name = "ContaConfirmacao", description = "API relacionada a confirmação de conta")
public class ContaConfirmacaoControlador {

    private final ContaConfirmacaoService confirmacaoServico;
    private final ContaServiceImpl contaServico;

    @GetMapping(ContaConfirmacaoEndpoints.ATIVACAO)
    public Resposta<String> ativacao(@RequestParam String token) {
        Resposta<String> resposta = new Resposta<>();
        ContaConfirmacao confirmacao = confirmacaoServico.findByToken(token);
        Conta conta = confirmacao.getConta();

        confirmacaoServico.validateConfirmation(confirmacao);
        confirmacaoServico.deleteById(confirmacao.getId());
        contaServico.activateAccount(conta);

        resposta.setEndpoint(ContaConfirmacaoEndpoints.ATIVACAO);
        resposta.setConteudo("Conta ativada com sucesso!");
        return resposta;
    }

}
