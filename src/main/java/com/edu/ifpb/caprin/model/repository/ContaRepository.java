package com.edu.ifpb.caprin.model.repository;

import com.edu.ifpb.caprin.model.entity.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

    Optional<Conta> findByEmail(String email);
    Optional<Conta> findByCPF(String CPF);
}
