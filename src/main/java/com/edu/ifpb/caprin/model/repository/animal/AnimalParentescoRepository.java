package com.edu.ifpb.caprin.model.repository.animal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edu.ifpb.caprin.model.entity.animal.AnimalParentesco;
@Repository
public interface AnimalParentescoRepository extends JpaRepository<AnimalParentesco, Long> {
}
