package com.edu.ifpb.caprin.apresentation.controller.conta;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.edu.ifpb.caprin.apresentation.compartilhado.ApiEndpoints;
import com.edu.ifpb.caprin.apresentation.compartilhado.ControladorCrud;
import com.edu.ifpb.caprin.apresentation.compartilhado.resposta.Resposta;
import com.edu.ifpb.caprin.apresentation.endpoints.ContaEndpoints;
import com.edu.ifpb.caprin.business.service.auth.PermissaoServico;
import com.edu.ifpb.caprin.business.service.impl.conta.ContaServiceImpl;
import com.edu.ifpb.caprin.model.dto.conta.ContaRequisicao;
import com.edu.ifpb.caprin.model.entity.conta.Conta;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(ContaEndpoints.PREFIXO)
@AllArgsConstructor
@Tag(name = "Conta", description = "API de Contas")
public class ContaController extends ControladorCrud<Conta, Long, ContaRequisicao, Resposta<Conta>> {

    private final ContaServiceImpl contaServico;

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Override
    public Page<Resposta<Conta>> findAll(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "linesPerPage", required = false, defaultValue = "10") int linesPerPage,
            @RequestParam(value = "direction", required = false, defaultValue = "asc") String direction,
            @RequestParam(value = "orderBy", required = false, defaultValue = "id") String orderBy) {

        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(orderBy).descending() : Sort.by(orderBy).ascending();
        Pageable pageable = PageRequest.of(page, linesPerPage, sort);

        Page<Conta> pageContas = contaServico.findAll(pageable);
        List<Conta> listaContas = pageContas.getContent();
        List<Resposta<Conta>> contaRespostas = listaContas.stream()
                .map(c -> criarContaResposta(ContaEndpoints.PREFIXO, c, null))
                .collect(Collectors.toList());

        Page<Resposta<Conta>> resposta = new PageImpl<>(contaRespostas, pageable, contaRespostas.size());
        return resposta;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Override
    public Resposta<Conta> findById(@PathVariable Long id) {
        Conta conta = contaServico.buscarPorID(id);
        return criarContaResposta(ApiEndpoints.ID, conta, null);
    }

    @Override
    public Resposta<Conta> register(@Valid @RequestBody ContaRequisicao request) {
        Conta conta = new Conta();
        String codigo = PermissaoServico.obterCodigo(request.getContaTipo(), conta.getContaTipo());

        BeanUtils.copyProperties(request, conta);
        conta.setContaTipo(codigo);
        conta.setDhCriacao(LocalDateTime.now());
        conta = contaServico.register(conta);
        return criarContaResposta(ContaEndpoints.PREFIXO, conta, null);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'ORGANIZADOR', 'EXPOSITOR')")
    @Override
    public Resposta<Conta> update(@PathVariable Long id, @RequestBody ContaRequisicao request) {
        Conta contaAtualizada = new Conta();
        BeanUtils.copyProperties(request, contaAtualizada);
        contaServico.update(id, contaAtualizada);
        return criarContaResposta(ApiEndpoints.ID, contaAtualizada, null);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Override
    public Resposta<Conta> delete(@PathVariable Long id) {
        contaServico.deleteById(id);
        return criarContaResposta(ApiEndpoints.ID, null, null);
    }

    private Resposta<Conta> criarContaResposta(String endpoint, Conta conteudo, List<String> erros) {
        Resposta<Conta> resposta = new Resposta<>();
        resposta.setEndpoint(endpoint);
        resposta.setConteudo(conteudo);
        resposta.setErros(erros);
        return resposta;
    }


}