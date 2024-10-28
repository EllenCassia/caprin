package com.edu.ifpb.caprin.apresentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.edu.ifpb.caprin.business.service.AnimalService;
import com.edu.ifpb.caprin.model.entity.Animal;

import java.util.List;

@RestController
@RequestMapping("/animal")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @PostMapping
    public ResponseEntity<Animal> criarAnimal(@RequestBody Animal animal) {
        Animal novoAnimal = animalService.criarAnimal(animal);
        return ResponseEntity.ok(novoAnimal);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Animal> atualizarAnimal(@PathVariable Long id, @RequestBody Animal novosDados) {
        Animal animalAtualizado = animalService.atualizarAnimal(id, novosDados);
        return ResponseEntity.ok(animalAtualizado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Animal> buscarPorId(@PathVariable Long id) {
        return animalService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Animal> listarTodos() {
        return animalService.listarTodos();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirAnimal(@PathVariable Long id) {
        animalService.excluirAnimal(id);
        return ResponseEntity.noContent().build();
    }
}

