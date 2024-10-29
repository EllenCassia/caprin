package com.edu.ifpb.caprin.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.ifpb.caprin.business.service.AnimalService;
import com.edu.ifpb.caprin.model.entity.Animal;
import com.edu.ifpb.caprin.model.repository.AnimalRepository;


import java.util.Optional;
import java.util.Date;
import java.util.List;

@Service
public class AnimalServiceImpl implements AnimalService {

    @Autowired
    private AnimalRepository animalRepository;
    
    // Método para criar um animal aplicando todas as validações de negócio
    
    
    public Animal criarAnimal(Animal animal) {
    
        validarRegistroUnico(animal);
        definirRegistro(animal);
        validarInconsistencias(animal);
        Animal animalSalvo = animalRepository.save(animal); // Salva o animal
        return animalSalvo; // Converte e retorna AnimalResposta
    
    }

    // Atualizar dados do animal existente

    public Animal atualizarAnimal(Long id, Animal novosDados) {
        
        Optional<Animal> optionalAnimal = animalRepository.findById(id);
        
        if (optionalAnimal.isPresent()) {
            Animal animalExistente = optionalAnimal.get();
            atualizarDadosAnimal(animalExistente, novosDados);
            validarRegistroUnico(animalExistente);
            definirRegistro(animalExistente);
            // definirClassificacao(animalExistente);
            validarInconsistencias(animalExistente);
            return animalRepository.save(animalExistente);
        }
        throw new RuntimeException("Animal com ID " + id + " não encontrado.");
    }

    // Buscar animal por ID

    public Optional<Animal> buscarPorId(Long id) {
        return animalRepository.findById(id);
    }

    // Listar todos os animais

    public List<Animal> listarTodos() {
        return animalRepository.findAll();
    }

    // Excluir animal por ID

    public void excluirAnimal(Long id) {
        animalRepository.deleteById(id);
    }

    // Validação de inconsistências
    
    private void validarInconsistencias(Animal animal) {

        if (animal.getDataNascimento() == null) {
            animal.setInconsistencia("Data de nascimento não informada.");
        } else if (animal.getTOD() == null || animal.getTOE() == null) {
            animal.setInconsistencia("TOD ou TOE ausentes.");
        } else if (!dentroDoPrazoGestacao(animal.getDataNascimento())) {
            animal.setInconsistencia("Fora do prazo de gestação.");
        } else {
            animal.setInconsistencia(null);
        }
    }

    // Definir o registro concatenando TOD e TOE
    
    private void definirRegistro(Animal animal) {
        animal.setRegistro(animal.getTOD() + animal.getTOE());
    }

    // Verificar se o registro é único

    private void validarRegistroUnico(Animal animal) {
        List<Animal> animais = animalRepository.findAll();
        for (Animal a : animais) {
            if (a.getRegistro().equals(animal.getRegistro()) && !a.getId().equals(animal.getId())) {
                throw new RuntimeException("Registro já existe para outro animal.");
            }
        }
    }

    // Definir a classificação do animal com base nos critérios de negócio

    // private void definirClassificacao(Animal animal) {
    //     // Exemplo simplificado: categorização baseada em atributos
    //     if (animal.getDNA()) {
    //         animal.setSituacao("PO"); // Exemplo de "Puros de Origem"
    //     } else {
    //         animal.setSituacao("CCG"); // Exemplo de "Cruzamento sob Controle de Genealogia"
    //     }
    // }

    // Verificar se a data está dentro do prazo de gestação permitido 

    private boolean dentroDoPrazoGestacao(Date dataNascimento) {
        return true;
    }

    // Atualiza os dados do animal, exceto o ID

    private void atualizarDadosAnimal(Animal existente, Animal novosDados) {
        
        existente.setIdSiscapri(novosDados.getIdSiscapri());
        existente.setDataExtracaoSiscapri(novosDados.getDataExtracaoSiscapri());
        existente.setDataNascimento(novosDados.getDataNascimento());
        existente.setSexo(novosDados.getSexo());
        existente.setDNA(novosDados.isDNA());
        existente.setTOD(novosDados.getTOD());
        existente.setTOE(novosDados.getTOE());
        existente.setNome(novosDados.getNome());
        existente.setRaca(novosDados.getRaca());
        existente.setPelagem(novosDados.getPelagem());
        existente.setCriador(novosDados.getCriador());
        existente.setProprietario(novosDados.getProprietario());
        existente.setAfixo(novosDados.getAfixo());
        existente.setSituacao(novosDados.getSituacao());
        existente.setInconsistencia(novosDados.getInconsistencia());

    }

}
