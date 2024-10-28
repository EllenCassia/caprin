package com.edu.ifpb.caprin.business.service;

import com.edu.ifpb.caprin.model.entity.Endereco;
import org.springframework.stereotype.Service;

@Service
public interface EnderecoService {

    public abstract Endereco create(Endereco endereco);
    public abstract Endereco update(Long id, Endereco novosDados);
    public abstract Endereco findById(Long id);
    public abstract void delete(Long id);
}
