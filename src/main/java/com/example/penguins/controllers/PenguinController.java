package com.example.penguins.controllers;

import com.example.penguins.Repositories.PenguinRepository;
import com.example.penguins.entities.Penguin;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/penguins")
public class PenguinController {

    PenguinRepository penguinRepository;

    PenguinController(PenguinRepository penguinRepository) {
        this.penguinRepository = penguinRepository;
    }

    @GetMapping
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("nice");
    }


    @PostMapping()
    public ResponseEntity<Penguin> createPenguin(@RequestBody Penguin penguin) {
        Penguin createdPenguin = penguinRepository.save(penguin);
        return ResponseEntity.created(URI.create("/penguins/" + createdPenguin.getId())).build();
    }
}
