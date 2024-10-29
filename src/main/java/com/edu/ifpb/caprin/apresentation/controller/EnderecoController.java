package com.edu.ifpb.caprin.apresentation.controller;

import com.edu.ifpb.caprin.business.service.EnderecoService;
import com.edu.ifpb.caprin.model.entity.Endereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    // Criação de um novo endereço
    @PostMapping
    public ResponseEntity<Endereco> create(@RequestBody Endereco endereco) {
        Endereco novoEndereco = enderecoService.create(endereco);
        return ResponseEntity.ok(novoEndereco);
    }

    // Atualização de um endereço existente
    @PutMapping("/{id}")
    public ResponseEntity<Endereco> update(@PathVariable Long id, @RequestBody Endereco enderecoAtualizado) {
        Endereco endereco = enderecoService.update(id, enderecoAtualizado);
        return ResponseEntity.ok(endereco);
    }

    // Deleção de um endereço
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        enderecoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
