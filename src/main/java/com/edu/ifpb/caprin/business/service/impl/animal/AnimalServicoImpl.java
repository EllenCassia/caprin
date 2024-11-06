package com.edu.ifpb.caprin.business.service.impl.animal;

import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.edu.ifpb.caprin.business.service.animal.AnimalService;
import com.edu.ifpb.caprin.business.service.exception.RgAlreadyExistsException;
import com.edu.ifpb.caprin.model.compartilhado.repositorio.DominioRepositorio;
import com.edu.ifpb.caprin.model.entity.animal.Animal;
import com.edu.ifpb.caprin.model.repository.animal.AnimalRepository;

import jakarta.transaction.Transactional;


@Service
@AllArgsConstructor
public class AnimalServicoImpl implements AnimalService {

    private final AnimalRepository animalRepositorio;

    @Override
    public DominioRepositorio<Animal, Long> getRepositorio() {
        return animalRepositorio;
    }

    @Override
    public Class<Animal> getDominioClasse() {
        return Animal.class;
    }

    @Override
    @Transactional
    public Animal register(Animal animal) {
        verificarRgExiste(animal.getRegistro());
        animal.setDhCriacao(LocalDateTime.now());
        return animalRepositorio.save(animal);
    }

    public void verificarRgExiste(String rg){

        List<Animal> animais = animalRepositorio.findAll();
        boolean rgExistente = animais.stream().anyMatch(animal -> animal.getRegistro().equals(rg));
        
        if (rgExistente) {
            throw new RgAlreadyExistsException("RG já cadastrado");
        }
    }

}