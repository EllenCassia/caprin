package com.edu.ifpb.caprin.model.repository;

import com.edu.ifpb.caprin.model.entity.Conta;
import com.edu.ifpb.caprin.model.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
