package com.example.penguins.entities;
import  com.example.penguins.enums.Species;
import jakarta.persistence.*;

@Entity
@Table(name = "Penguins")
public class Penguin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private Integer hunger;
    private Integer love;

    @Enumerated(EnumType.STRING)
    private Species species;

    // Default constructor is needed for JPA
    public Penguin() {
    }

    public Penguin(String name, Integer hunger, Integer love, Species species) {
        this.name = name;
        this.hunger = hunger;
        this.love = love;
        this.species = species;
    }

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHunger() {
        return hunger;
    }

    public void setHunger(Integer hunger) {
        this.hunger = hunger;
    }

    public Integer getLove() {
        return love;
    }

    public void setLove(Integer love) {
        this.love = love;
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }
}