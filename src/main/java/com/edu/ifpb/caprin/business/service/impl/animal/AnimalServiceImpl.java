 package com.edu.ifpb.caprin.business.service.impl.animal;

 import com.edu.ifpb.caprin.business.service.animal.AnimalService;
 import com.edu.ifpb.caprin.model.compartilhado.repositorio.DominioRepositorio;
 import com.edu.ifpb.caprin.model.entity.animal.Animal;
 import com.edu.ifpb.caprin.model.repository.animal.AnimalRepository;
 import lombok.AllArgsConstructor;
 import org.springframework.stereotype.Service;

 @Service
 @AllArgsConstructor
 public class AnimalServiceImpl implements AnimalService {

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
     public Animal register(Animal animal) {
         return animalRepositorio.save(animal);
 }