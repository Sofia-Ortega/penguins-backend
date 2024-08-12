package com.example.penguins.configurations;

import com.example.penguins.repositories.PenguinRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class OnStartup {
    PenguinRepository penguinRepository;

    OnStartup(PenguinRepository penguinRepository) {
        this.penguinRepository = penguinRepository;
    }

    @PostConstruct
    private void postConstruct() {
        System.out.println("Post construct!!!!!!!!!!!!");
    }

}
