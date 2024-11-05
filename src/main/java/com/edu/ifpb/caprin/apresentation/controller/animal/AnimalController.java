 package com.edu.ifpb.caprin.apresentation.controller.animal;
 import com.edu.ifpb.caprin.apresentation.compartilhado.ApiEndpoints;
 import com.edu.ifpb.caprin.apresentation.compartilhado.ControladorCrud;
 import com.edu.ifpb.caprin.apresentation.compartilhado.resposta.Resposta;
 import com.edu.ifpb.caprin.apresentation.endpoints.AnimalEndpoints;
 import com.edu.ifpb.caprin.business.service.animal.AnimalService;
 import com.edu.ifpb.caprin.model.dto.animal.AnimalRequisicao;
 import com.edu.ifpb.caprin.model.entity.animal.Animal;
 import lombok.AllArgsConstructor;
 import org.springframework.data.domain.Page;
 import org.springframework.security.access.prepost.PreAuthorize;
 import org.springframework.web.bind.annotation.PathVariable;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RestController;

 import java.time.LocalDateTime;
 import java.util.List;

 @RestController
 @RequestMapping(AnimalEndpoints.PREFIXO)
 @AllArgsConstructor
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
         return criarAnimalResposta(ApiEndpoints.ID, animal, null);
     }

     @Override
     public Resposta<Animal> register(AnimalRequisicao request) {
         Animal animal = new Animal();
         animal.setDhCriacao(LocalDateTime.now());
         animal = animalServico.register(animal);
         return criarAnimalResposta(AnimalEndpoints.PREFIXO, animal,null);
     }

     @Override
     public Resposta<Animal> update(Long id, AnimalRequisicao request) {
         return null;
     }

     @Override
     public Resposta<?> delete(Long id) {
         return null;
     }

     private Resposta<Animal> criarAnimalResposta(String endpoint, Animal conteudo, List<String> erros) {
         Resposta<Animal> resposta = new Resposta<>();
         resposta.setEndpoint(endpoint);
         resposta.setConteudo(conteudo);
         resposta.setErros(erros);
         return resposta;
     }
 }