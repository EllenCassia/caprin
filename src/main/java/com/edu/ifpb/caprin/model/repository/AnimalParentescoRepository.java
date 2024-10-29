package com.edu.ifpb.caprin.model.repository;

import com.edu.ifpb.caprin.model.entity.Animal;
import com.edu.ifpb.caprin.model.entity.AnimalParentesco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalParentescoRepository extends JpaRepository<AnimalParentesco, Long> {
}
