package com.edu.ifpb.caprin.apresentation.controller.animal;

import lombok.AllArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.ifpb.caprin.apresentation.compartilhado.ApiEndpoints;
import com.edu.ifpb.caprin.apresentation.compartilhado.ControladorCrud;
import com.edu.ifpb.caprin.apresentation.compartilhado.resposta.Resposta;
import com.edu.ifpb.caprin.business.service.animal.AnimalService;
import com.edu.ifpb.caprin.model.dto.animal.AnimalRequisicao;
import com.edu.ifpb.caprin.model.dto.conta.ContaRequisicao;
import com.edu.ifpb.caprin.model.entity.animal.Animal;
import com.edu.ifpb.caprin.model.utils.BeanUtils;

import io.swagger.v3.oas.annotations.tags.Tag;

import com.edu.ifpb.caprin.apresentation.endpoints.AnimalEndpoints;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(AnimalEndpoints.PREFIXO)
@AllArgsConstructor
@Tag(name = "Animal", description = "API de animais")
public class AnimalController extends ControladorCrud<Animal, Long, AnimalRequisicao, Resposta<Animal>> {

    private final AnimalService animalServico;

    @Override
    public Page<Resposta<Animal>> findAll(int page, int linesPerPage, String direction, String orderBy) {
        return null;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Override
    public Resposta<Animal> findById(@PathVariable Long id) {
        Animal animal = animalServico.buscarPorID(id);
        return criarResposta(ApiEndpoints.ID, animal, null);
    }

    @Override
    public Resposta<Animal> register(AnimalRequisicao request) {
        Animal animal = new Animal();
        BeanUtils.copyProperties(request, animal);
        animal.setDhCriacao(LocalDateTime.now());
        animal = animalServico.register(animal);
        return criarResposta(ApiEndpoints.ADICIONAR, animal, null);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'ORGANIZADOR', 'EXPOSITOR')")
    @Override
    public Resposta<Animal> update(@PathVariable Long id, @RequestBody AnimalRequisicao request) {
        Animal animalAtualizado = new Animal();
        BeanUtils.copyProperties(request, animalAtualizado);
        animalAtualizado = animalServico.update(id, animalAtualizado);
        return criarResposta(ApiEndpoints.ID, animalAtualizado, null);
    }

    @Override
    public Resposta<?> delete(Long id) {
        animalServico.deleteById(id);
        return criarResposta(ApiEndpoints.ID, null, null);
    }

    private Resposta<Animal> criarResposta(String endpoint, Animal conteudo, List<String> erros) {
        Resposta<Animal> resposta = new Resposta<>();
        resposta.setEndpoint(endpoint);
        resposta.setConteudo(conteudo);
        resposta.setErros(erros);
        return resposta;
    }
}
