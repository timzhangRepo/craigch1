package com.example.craigch1.cruddata;

import com.example.craigch1.DTO.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepoData extends CrudRepository<Ingredient, String> {
}
