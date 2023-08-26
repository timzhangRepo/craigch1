package com.example.craigch1;

import com.example.craigch1.DTO.Ingredient;
import com.example.craigch1.DTO.Ingredient.Type;
import com.example.craigch1.cruddata.IngredientRepoData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.Optional;


@SpringBootApplication
public class Craigch1Application {

    Logger logger = LoggerFactory.getLogger("Craigch1Application.class");

    public static void main(String[] args) {
        SpringApplication.run(Craigch1Application.class, args);
    }

    @Bean
    //When the application starts up, any beans in the application context that implement CommandLineRunner or ApplicationRunner will have their run() methods invoked after the application context and all beans are wired up
    public CommandLineRunner dataLoader(IngredientRepoData repo) {
        logger.info("what is being put into the logger factory?");
        return args -> {
            repo.save(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));
            repo.save(new Ingredient("COTO", "Corn Tortilla", Type.WRAP));
            repo.save(new Ingredient("GRBF", "Ground Beef", Type.PROTEIN));
            repo.save(new Ingredient("CARN", "Carnitas", Type.PROTEIN));
            repo.save(new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES));
            repo.save(new Ingredient("LETC", "Lettuce", Type.VEGGIES));
            repo.save(new Ingredient("CHED", "Cheddar", Type.CHEESE));
            repo.save(new Ingredient("JACK", "Monterrey Jack", Type.CHEESE));
            repo.save(new Ingredient("SLSA", "Salsa", Type.SAUCE));
            repo.save(new Ingredient("SRCR", "Sour Cream", Type.SAUCE));

            Ingredient ingredient = repo.helper();
            logger.info(ingredient.toString());
        };

    }
}
