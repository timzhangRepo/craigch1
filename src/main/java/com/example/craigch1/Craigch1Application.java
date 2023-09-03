package com.example.craigch1;

import com.example.craigch1.DTO.TacoUser;
import com.example.craigch1.cruddata.UserRepository;
import com.example.craigch1.model.Ingredient;
import com.example.craigch1.model.Ingredient.Type;
import com.example.craigch1.cruddata.IngredientRepoData;
import com.example.craigch1.repository.IngredientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;


@SpringBootApplication
public class Craigch1Application {

    Logger logger = LoggerFactory.getLogger("Craigch1Application.class");

    public static void main(String[] args) {
        SpringApplication.run(Craigch1Application.class, args);
    }

    @Bean
    //When the application starts up, any beans in the application context that implement CommandLineRunner or ApplicationRunner will have their run() methods invoked after the application context and all beans are wired up
    public CommandLineRunner dataLoader(IngredientRepository repo) {
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
        };
    }


    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepo) {
        //如果没有UserDetailsService, 就会自动返回给你一个password，username就是user
        return username -> {
            TacoUser user = userRepo.findByUsername(username);
            if (user != null) return user;
            throw new UsernameNotFoundException("User " + username + "not found");
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //顺序matters，如果这两个反过来放就会出问题
        http.authorizeRequests(customizer -> {
                    try {
                        customizer
                                .requestMatchers(new AntPathRequestMatcher("/design"), new AntPathRequestMatcher("/orders"))
                                .hasRole("USER")
                                .requestMatchers(new AntPathRequestMatcher("/"), new AntPathRequestMatcher("/**"))
                                .permitAll()
                                .and().formLogin().loginPage("/login");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
        );
        return http.build();
    }


}
