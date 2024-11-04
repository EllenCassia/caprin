package com.edu.ifpb.caprin.business.service.animal;


import java.util.List;
import java.util.Optional;

import com.edu.ifpb.caprin.model.entity.animal.Animal;
import com.edu.ifpb.caprin.model.entity.animal.AnimalParentesco;

public interface AnimalParentescoService {
    List<AnimalParentesco> findAll();
    Optional<AnimalParentesco> findById(String registro);
    AnimalParentesco save(AnimalParentesco animalParentesco);
    void deleteById(String registro);
    public abstract Animal buscarParentesco(Animal animal);
    public AnimalParentesco createAnimalParentescoFromSiscapri(String registro, String url) throws Exception;
}
