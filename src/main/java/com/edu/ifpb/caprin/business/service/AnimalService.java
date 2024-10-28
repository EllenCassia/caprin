package com.edu.ifpb.caprin.business.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.edu.ifpb.caprin.model.entity.Animal;

@Service
public interface AnimalService {

    public abstract Animal criarAnimal(Animal animal);

    public abstract Animal atualizarAnimal(Long id, Animal novosDados);

    public abstract Optional<Animal> buscarPorId(Long id);

    public abstract List<Animal> listarTodos();

    public abstract void excluirAnimal(Long id);

}