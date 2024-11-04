package com.edu.ifpb.caprin.apresentation.controller.login;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.edu.ifpb.caprin.apresentation.compartilhado.resposta.Resposta;
import com.edu.ifpb.caprin.apresentation.endpoints.LoginEndpoints;
import com.edu.ifpb.caprin.business.service.auth.AutenticadorService;
import com.edu.ifpb.caprin.model.dto.login.LoginRequisicao;
import com.edu.ifpb.caprin.model.dto.token.TokenResposta;
import com.edu.ifpb.caprin.model.utils.TokenJwtUtils;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.*;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(LoginEndpoints.PREFIXO)
@AllArgsConstructor
public class LoginController {

    private final AutenticadorService autenticador;

    private final TokenJwtUtils tokenJwtUtils;

    @PostMapping(LoginEndpoints.LOGIN)
    @ResponseStatus(OK)
    public Resposta<TokenResposta> login(@RequestBody LoginRequisicao requisicao) {
        Resposta<TokenResposta> resposta = new Resposta<>();
        var auth = new UsernamePasswordAuthenticationToken(requisicao.getEmail(), requisicao.getSenha());
        var authenticated = autenticador.authenticate(auth);
        TokenResposta token = tokenJwtUtils.gerarToken(authenticated);

        resposta.setEndpoint(LoginEndpoints.LOGIN);
        resposta.setConteudo(token);
        return resposta;
    }

    @PostMapping(LoginEndpoints.VALIDAR_TOKEN)
    @ResponseStatus(OK)
    public Resposta<String> validarToken(@RequestParam String token) { 
        Resposta<String> resposta = new Resposta<>();
        
        tokenJwtUtils.validarToken(token);

        resposta.setEndpoint(LoginEndpoints.VALIDAR_TOKEN);
        resposta.setConteudo("Token válido!");
        return resposta;
    }

    
}
