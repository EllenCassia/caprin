package com.edu.ifpb.caprin.model.conta;

import java.time.LocalDateTime;

import com.edu.ifpb.caprin.model.entity.conta.Conta;


public abstract class ContaUtils {

    public static Conta criarConta() {
        Conta conta = new Conta();
        conta.setDhCriacao(LocalDateTime.now());
        conta.setId(1L);
        conta.setEmail("john.doe@gmail.com");
        conta.setSenha("john123");
        conta.setCpf("69001945082");
        conta.setContaTipo("000");
        conta.setTelefoneDDD(Short.parseShort("87"));
        conta.setTelefone(Long.parseLong("991218046"));
        conta.setEndereco(EnderecoUtils.criarEndereco());
        return conta;
    }

    
}
