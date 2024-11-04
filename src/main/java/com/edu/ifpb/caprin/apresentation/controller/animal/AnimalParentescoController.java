package com.edu.ifpb.caprin.apresentation.controller.animal;

import com.edu.ifpb.caprin.business.service.animal.AnimalParentescoService;
import com.edu.ifpb.caprin.model.entity.animal.AnimalParentesco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/animalparentesco")
public class AnimalParentescoController {

    private final AnimalParentescoService animalParentescoService;

    @Autowired
    public AnimalParentescoController(AnimalParentescoService animalParentescoService) {
        this.animalParentescoService = animalParentescoService;
    }

    @GetMapping
    public ResponseEntity<List<AnimalParentesco>> findAll() {
        List<AnimalParentesco> animalParentescos = animalParentescoService.findAll();
        return ResponseEntity.ok(animalParentescos);
    }

    @GetMapping("/{registro}")
    public ResponseEntity<AnimalParentesco> findById(@PathVariable String registro) {
        return animalParentescoService.findById(registro)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AnimalParentesco> create(@RequestBody AnimalParentesco animalParentesco) {
        AnimalParentesco savedAnimalParentesco = animalParentescoService.save(animalParentesco);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAnimalParentesco);
    }

    @PutMapping("/{registro}")
    public ResponseEntity<AnimalParentesco> update(@PathVariable String registro, @RequestBody AnimalParentesco animalParentesco) {
        if (!animalParentescoService.findById(registro).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        animalParentesco.setRegistro(registro);
        AnimalParentesco updatedAnimalParentesco = animalParentescoService.save(animalParentesco);
        return ResponseEntity.ok(updatedAnimalParentesco);
    }

    @DeleteMapping("/{registro}")
    public ResponseEntity<Void> deleteById(@PathVariable String registro) {
        if (!animalParentescoService.findById(registro).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        animalParentescoService.deleteById(registro);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/criar")
    public ResponseEntity<AnimalParentesco> criarAnimalParentesco(@RequestParam String registro) {
        try {
            // Chama o método para criar o AnimalParentesco
            AnimalParentesco animalParentesco = animalParentescoService.createAnimalParentescoFromSiscapri(registro, null);
            return ResponseEntity.ok(animalParentesco);
        } catch (Exception e) {
            // Em caso de erro, retorna um status HTTP adequado
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }
}
