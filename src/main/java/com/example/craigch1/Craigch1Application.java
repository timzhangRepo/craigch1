package com.example.craigch1;

import com.example.craigch1.DTO.Ingredient;
import com.example.craigch1.DTO.Ingredient.Type;
import com.example.craigch1.cruddata.IngredientRepoData;
import com.example.craigch1.data.IngredientRepository;
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
            logger.info("command line is running");
            Ingredient saved = repo.save(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));
            logger.info("saved {}", saved.toString());
            Optional<Ingredient> retrived  = repo.findById("FLTO");
            if(retrived.isPresent()){
                logger.info("retrived {}", retrived.get());
            }else{
                logger.info("doesn't exist");
            }
        };

    }
}
