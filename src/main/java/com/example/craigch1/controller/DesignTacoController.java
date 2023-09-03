package com.example.craigch1.controller;


import com.example.craigch1.model.Ingredient;
import com.example.craigch1.model.Ingredient.*;
import com.example.craigch1.model.Taco;
import com.example.craigch1.model.TacoOrder;
import com.example.craigch1.cruddata.IngredientRepoData;
import com.example.craigch1.repository.IngredientRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/design")
@Controller
@SessionAttributes("tacoOrder")
@Slf4j
public class DesignTacoController {
    private static final Logger logger = LoggerFactory.getLogger(DesignTacoController.class);
    private final IngredientRepository ingredientRepoData;
    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepoData) {
        this.ingredientRepoData = ingredientRepoData;
    }
    @GetMapping
    public String showDesignForm() {
        logger.info("showDesignForm called");
        return "design";
    }
    @PostMapping
    public String onReceive(@Valid Taco taco , Errors errors, @ModelAttribute TacoOrder tacoOrder){
        if(errors.hasErrors()){
            return "design";
        }
        tacoOrder.addTaco(taco);
        log.info("Processing taco {}", taco);
        return "redirect:/orders/current";
    }
    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        Iterable<Ingredient> ingredients = ingredientRepoData.findAll();
        List<Ingredient> ingredientsList = new ArrayList<>();
        for(Ingredient ingredient: ingredients){
            ingredientsList.add(ingredient);
        }
// 这里我们将所有的model attribute添加到了一个数据库里。
//        List<Ingredient> ingredients = Arrays.asList(
//                new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP),
//                new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP),
//                new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
//                new Ingredient("CARN", "Carnitas", Type.PROTEIN),
//                new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
//                new Ingredient("LETC", "Lettuce", Type.VEGGIES),
//                new Ingredient("CHED", "Cheddar", Type.CHEESE),
//                new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
//                new Ingredient("SLSA", "Salsa", Type.SAUCE),
//                new Ingredient("SRCR", "Sour Cream", Type.SAUCE),
//                new Ingredient("GLMG", "Lao Gan Ma", Type.SAUCE)
//        );
        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredientsList, type));
        }
    }
    private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList());
    }
    @ModelAttribute(name = "taco")
    public Taco taco(){
        return new Taco();
    }
    @ModelAttribute(name = "tacoOrder")
    public TacoOrder tacoOrder(){
        return new TacoOrder();
    }
}
