package com.edu.ifpb.caprin.model.repository.conta;

import org.springframework.stereotype.Repository;

import com.edu.ifpb.caprin.model.compartilhado.repositorio.DominioRepositorio;
import com.edu.ifpb.caprin.model.entity.conta.Conta;

import java.util.Optional;

@Repository
public interface ContaRepository extends DominioRepositorio<Conta, Long> {

    Optional<Conta> findByEmail(String email);
    Optional<Conta> findByCpf(String cpf);
}
