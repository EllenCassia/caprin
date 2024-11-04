package com.edu.ifpb.caprin.model.repository.conta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edu.ifpb.caprin.model.entity.conta.Endereco;
@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
