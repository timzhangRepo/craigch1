package com.example.craigch1.cruddata;

import com.example.craigch1.DTO.Ingredient;
import com.example.craigch1.DTO.TacoOrder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IngredientRepoData extends CrudRepository<Ingredient, String> {

    @Query("select o from Ingredient o where o.name = 'Flour Tortilla'")
    public Ingredient helper();
}
