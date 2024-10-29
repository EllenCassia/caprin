package com.edu.ifpb.caprin.business.service;

import com.edu.ifpb.caprin.model.entity.Conta;

public interface ContaService {

    public abstract Conta create(Conta conta);
    public abstract Conta update(Long id, Conta novosDados);
    public abstract Conta findById(Long id);
    public abstract void delete(Long id);
    
}
