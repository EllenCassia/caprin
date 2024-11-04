package com.edu.ifpb.caprin.model.conta;

import com.edu.ifpb.caprin.model.entity.conta.Endereco;

public abstract class EnderecoUtils {

        public static Endereco criarEndereco() {
        Endereco endereco = new Endereco();
        endereco.setId(1L);
        endereco.setCep("5850000");
        endereco.setCidade("Monteiro");
        endereco.setComplemento("APT 1");
        endereco.setEstado("Paraiba");
        endereco.setNumero("1000");
        endereco.setLogradouro("Av. Aryton Senna");
        return endereco;
    }
    
}
