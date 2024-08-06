package com.example.penguins.controllers;

import com.example.penguins.Repositories.PenguinRepository;
import com.example.penguins.entities.Penguin;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("penguins")
public class PenguinController {

    PenguinRepository penguinRepository;

    PenguinController(PenguinRepository penguinRepository) {
        this.penguinRepository = penguinRepository;
    }

    @PostMapping("/")
    public Penguin createPenguin(@RequestBody Penguin penguin) {
        return penguinRepository.save(penguin);
    }
}
