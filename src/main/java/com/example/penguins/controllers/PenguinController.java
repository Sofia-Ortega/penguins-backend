package com.example.penguins.controllers;

import com.example.penguins.Repositories.PenguinRepository;
import com.example.penguins.entities.Penguin;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

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
}
