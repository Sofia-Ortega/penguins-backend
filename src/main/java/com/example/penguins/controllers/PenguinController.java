package com.example.penguins.controllers;

import com.example.penguins.repositories.PenguinRepository;
import com.example.penguins.entities.Penguin;
import com.example.penguins.structs.PenguinRequest;
import com.example.penguins.structs.Species;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/penguins")
public class PenguinController {

    PenguinRepository penguinRepository;

    PenguinController(PenguinRepository penguinRepository) {
        this.penguinRepository = penguinRepository;
    }

    @PostMapping()
    public ResponseEntity<Penguin> createPenguin(@RequestBody Penguin penguin) {
        Penguin createdPenguin = penguinRepository.save(penguin);
        return ResponseEntity.created(URI.create("/penguins/" + createdPenguin.getId())).build();
    }

    @GetMapping
    public ResponseEntity<Iterable<Penguin>> getPenguins() {
        Iterable<Penguin> penguins = penguinRepository.findAll();
        return ResponseEntity.ok(penguins);
    }

    @PostMapping("/feed/{penguinId}")
    public ResponseEntity<Integer> feedPenguin(@PathVariable Integer penguinId) {
        Optional<Penguin> optionalPenguin = penguinRepository.findById(penguinId);
        if(optionalPenguin.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Penguin penguin = optionalPenguin.get();

        if(penguin.getHunger() == 0) return ResponseEntity.badRequest().build();

        penguin.setHunger(penguin.getHunger() - 1);
        if(penguin.getHunger() < 0) penguin.setHunger(0);

        Penguin updatedPenguin = penguinRepository.save(penguin);

        return ResponseEntity.ok(updatedPenguin.getHunger());

    }

    @DeleteMapping("/{penguinId}")
    public ResponseEntity<Void> deletePenguin(@PathVariable Integer penguinId) {
        if (!penguinRepository.existsById(penguinId)) {
            return ResponseEntity.notFound().build();
        }

        penguinRepository.deleteById(penguinId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{penguinId}")
    public ResponseEntity<Penguin> updatePenguin(@PathVariable Integer penguinId, @RequestBody PenguinRequest penguinRequest) {
        Optional<Penguin> optionalPenguin = penguinRepository.findById(penguinId);
        if (optionalPenguin.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Penguin penguin = optionalPenguin.get();
        penguin.setName(penguinRequest.getName());
        try {
            penguin.setSpecies(Species.valueOf(penguinRequest.getSpecies().toUpperCase()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }

        Penguin updatedPenguin = penguinRepository.save(penguin);
        return ResponseEntity.ok(updatedPenguin);
    }
}
