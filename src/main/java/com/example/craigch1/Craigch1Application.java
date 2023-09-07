package com.example.craigch1;

import com.example.craigch1.DTO.TacoUser;
import com.example.craigch1.repository.UserRepository;
import com.example.craigch1.DTO.Ingredient;
import com.example.craigch1.DTO.Ingredient.Type;
import com.example.craigch1.repository.IngredientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@SpringBootApplication
public class Craigch1Application {

    Logger logger = LoggerFactory.getLogger(Craigch1Application.class);

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
        return username -> {
            try {
                TacoUser user = userRepo.findByUsername(username);
                if (user != null) {
                    logger.info("Login attempt for user {}, {}", username, user.toString());
                    return user;
                } else {
                    logger.warn("User not found: {}", username); // 添加警告日志
                    throw new UsernameNotFoundException("User " + username + " not found");
                }
            } catch (Exception e) {
                logger.error("An error occurred while finding user: {}", username, e);  // 添加错误日志
                throw new UsernameNotFoundException("User " + username + " not found");
            }
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
                                .and().formLogin()
                                    .loginPage("/login")
                                        .loginProcessingUrl("/authenticate")
                                .and()
                                    .oauth2Login()
                                        .loginPage("/login")
                                .defaultSuccessUrl("/design", true)
                                .and().logout().logoutSuccessUrl("/");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
        );
        return http.build();
    }
}