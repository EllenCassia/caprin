package com.edu.ifpb.caprin.business.service.impl;


import com.edu.ifpb.caprin.business.service.ContaService;
import com.edu.ifpb.caprin.model.entity.conta.Conta;
import com.edu.ifpb.caprin.model.repository.conta.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContaServiceImpl implements ContaService {

    private final ContaRepository contaRepository;

    @Autowired
    public ContaServiceImpl(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    @Override
    public Conta salvarConta(Conta conta) {
        return contaRepository.save(conta);
    }

    @Override
    public Optional<Conta> buscarPorId(Long id) {
        return contaRepository.findById(id);
    }

    @Override
    public Optional<Conta> buscarPorEmail(String email) {
        return contaRepository.findByEmail(email);
    }

    @Override
    public Optional<Conta> buscarPorCpf(String cpf) {
        return contaRepository.findByCpf(cpf);
    }

    @Override
    public List<Conta> buscarTodasContas() {
        return contaRepository.findAll();
    }

    @Override
    public Conta atualizarConta(Long id, Conta conta) {
        if (contaRepository.existsById(id)) {
            conta.setId(id);
            return contaRepository.save(conta);
        } else {
            throw new RuntimeException("Conta não encontrada para o ID fornecido");
        }
    }

    @Override
    public void deletarConta(Long id) {
        if (contaRepository.existsById(id)) {
            contaRepository.deleteById(id);
        } else {
            throw new RuntimeException("Conta não encontrada para o ID fornecido");
        }
    }
}

