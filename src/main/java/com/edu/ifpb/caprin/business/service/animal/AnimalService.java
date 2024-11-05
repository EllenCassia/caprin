package com.edu.ifpb.caprin.business.service.animal;

import java.util.List;
import java.util.Optional;

import com.edu.ifpb.caprin.business.service.compartilhado.servico.DominioServico;
import org.springframework.stereotype.Service;

import com.edu.ifpb.caprin.model.entity.animal.Animal;

public interface AnimalService extends DominioServico<Animal, Long> {
//
//    public abstract Animal criarAnimal(Animal animal);
//
//    public abstract Animal atualizarAnimal(Long id, Animal novosDados);
//
//    public abstract Optional<Animal> buscarPorId(Long id);
//
//    public abstract List<Animal> listarTodos();
//
//    public abstract void excluirAnimal(Long id);

    public abstract Animal register(Animal animal);

}