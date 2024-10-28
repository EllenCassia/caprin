package com.edu.ifpb.caprin.model.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edu.ifpb.caprin.model.entity.Animal;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
    
}
