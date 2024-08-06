package com.example.penguins.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Penguins")
public class Penguin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer hunger;
    private Integer love;

    // Default constructor is needed for JPA
    public Penguin() {
    }

    // Constructor with parameters
    public Penguin(String name, Integer hunger, Integer love) {
        this.name = name;
        this.hunger = hunger;
        this.love = love;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
}