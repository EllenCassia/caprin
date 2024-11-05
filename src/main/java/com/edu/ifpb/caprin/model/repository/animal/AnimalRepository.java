package com.edu.ifpb.caprin.model.repository.animal;
import com.edu.ifpb.caprin.model.compartilhado.repositorio.DominioRepositorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edu.ifpb.caprin.model.entity.animal.Animal;

@Repository
public interface AnimalRepository extends DominioRepositorio<Animal, Long> {
    
}
