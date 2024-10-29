package com.edu.ifpb.caprin.business.service;


import com.edu.ifpb.caprin.model.entity.AnimalParentesco;

import java.util.List;
import java.util.Optional;

public interface AnimalParentescoService {
    List<AnimalParentesco> findAll();
    Optional<AnimalParentesco> findById(String registro);
    AnimalParentesco save(AnimalParentesco animalParentesco);
    void deleteById(String registro);
}
