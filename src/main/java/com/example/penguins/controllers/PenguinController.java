package com.example.penguins.controllers;

import com.example.penguins.repositories.PenguinRepository;
import com.example.penguins.entities.Penguin;
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
        penguin.setHunger(penguin.getHunger() - 1);

        Penguin updatedPenguin = penguinRepository.save(penguin);

        return ResponseEntity.ok(updatedPenguin.getHunger());

    }
}
