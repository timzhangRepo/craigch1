package com.example.craigch1.DTO;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;


@Data
@Entity
public class Ingredient {
    @Id  //这里用jarkata的
    private final String id;
    private final String name;
    private final Type type;

    // No-argument constructor with private access
    protected Ingredient() {
        this.id = null; // or some other default value
        this.name = null; // or some other default value
        this.type = null; // or some other default value
    }

    // All-argument constructor
    public Ingredient(String id, String name, Type type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }
    public enum Type{
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}
