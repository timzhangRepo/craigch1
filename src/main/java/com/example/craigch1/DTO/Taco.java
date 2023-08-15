package com.example.craigch1.DTO;

import lombok.Data;

import java.util.List;
@Data
public class Taco {
    private String name;
    private List<Ingredient> ingredients;
}
