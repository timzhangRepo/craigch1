package com.example.craigch1.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Data
@Table
@AllArgsConstructor
public class Ingredient {
    @Id
    private final String id;
    private final String name;
    private final Type type;
    public enum Type{
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}
