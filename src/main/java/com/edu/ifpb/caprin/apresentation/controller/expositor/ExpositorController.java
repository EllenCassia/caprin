package com.edu.ifpb.caprin.apresentation.controller.expositor;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.ifpb.caprin.apresentation.compartilhado.ApiEndpoints;
import com.edu.ifpb.caprin.apresentation.compartilhado.ControladorCrud;
import com.edu.ifpb.caprin.apresentation.compartilhado.resposta.Resposta;
import com.edu.ifpb.caprin.business.service.expositor.ExpositorService;
import com.edu.ifpb.caprin.model.entity.expositor.Expositor;
import com.edu.ifpb.caprin.apresentation.endpoints.*;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(ExpositorEndpoints.PREFIXO)
@AllArgsConstructor
@Tag(name = "Expositor", description = "API de Expositor")
public class ExpositorController extends ControladorCrud<Expositor, Long, Expositor, Resposta<Expositor>>{

    private final ExpositorService expositorService;

    @Override
    public Page<Resposta<Expositor>> findAll(int page, int linesPerPage, String direction, String orderBy) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public Resposta<Expositor> findById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public Resposta<Expositor> register(@Valid @RequestBody Expositor request) {
        Expositor expositor = expositorService.register(request);
        return criarResposta(ApiEndpoints.ADICIONAR, expositor, null);
    }

    @Override
    public Resposta<Expositor> update(Long id, Expositor request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Resposta<?> delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

     private Resposta<Expositor> criarResposta(String endpoint, Expositor conteudo, List<String> erros) {
        Resposta<Expositor> resposta = new Resposta<>();
        resposta.setEndpoint(endpoint);
        resposta.setConteudo(conteudo);
        resposta.setErros(erros);
        return resposta;
    }
    
}
