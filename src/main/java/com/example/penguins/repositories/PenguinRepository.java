package com.example.penguins.repositories;

import com.example.penguins.entities.Penguin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PenguinRepository extends CrudRepository<Penguin, Integer> {
}
