package com.edu.ifpb.caprin.business.service;

import com.edu.ifpb.caprin.model.entity.Conta;
import java.util.List;
import java.util.Optional;

public interface ContaService {
    Conta salvarConta(Conta conta);
    Optional<Conta> buscarPorId(Long id);
    Optional<Conta> buscarPorEmail(String email);
    Optional<Conta> buscarPorCpf(String cpf);
    List<Conta> buscarTodasContas();
    Conta atualizarConta(Long id, Conta conta);
    void deletarConta(Long id);
}
