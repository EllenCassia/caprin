package com.edu.ifpb.caprin.business.service.impl;

import com.edu.ifpb.caprin.business.service.AnimalParentescoService;
import com.edu.ifpb.caprin.model.entity.AnimalParentesco;
import com.edu.ifpb.caprin.model.repository.AnimalParentescoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class AnimalParentescoServiceImpl implements AnimalParentescoService {

    private final AnimalParentescoRepository animalParentescoRepository;

    @Autowired
    public AnimalParentescoServiceImpl(AnimalParentescoRepository animalParentescoRepository) {
        this.animalParentescoRepository = animalParentescoRepository;
    }

    @Override
    public List<AnimalParentesco> findAll() {
        return animalParentescoRepository.findAll();
    }

    @Override
    public Optional<AnimalParentesco> findById(String registro) {
        return animalParentescoRepository.findById(Long.valueOf(registro));
    }

    @Override
    @Transactional
    public AnimalParentesco save(AnimalParentesco animalParentesco) {
        return animalParentescoRepository.save(animalParentesco);
    }

    @Override
    @Transactional
    public void deleteById(String registro) {
        animalParentescoRepository.deleteById(Long.valueOf(registro));
    }
}
