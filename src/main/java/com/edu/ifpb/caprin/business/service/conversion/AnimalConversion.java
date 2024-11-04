package com.edu.ifpb.caprin.business.service.conversion;

import com.edu.ifpb.caprin.model.dto.animal.AnimalResposta;
import com.edu.ifpb.caprin.model.entity.animal.Animal;

public class AnimalConversion {

    // Método para converter Animal em AnimalResposta

    public static AnimalResposta converterParaResposta(Animal animal) {
        
        AnimalResposta resposta = new AnimalResposta();
        
        resposta.setRegistro(animal.getRegistro()); 
        resposta.setNome(animal.getNome());
        resposta.setRaca(animal.getRaca());
        resposta.setCriador(animal.getCriador());
        resposta.setProprietario(animal.getProprietario());
        resposta.setDataNascimento(animal.getDataNascimento().toString());
        resposta.setSexo(Character.toString(animal.getSexo()));

        // // Adicionando apenas IDs para os relacionamentos
        // resposta.setFilhospaiIds(animal.getFilhospai().stream().map(filho -> filho.getId()).toList());
        // resposta.setFilhosmaeIds(animal.getFilhosmae().stream().map(filho -> filho.getId()).toList());

        // resposta.setInscricoesIds(animal.getInscricoes().stream().map(inscricao -> inscricao.getId()).toList());

        return resposta;
    }
    
}
