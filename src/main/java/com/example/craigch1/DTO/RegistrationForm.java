package com.example.craigch1.DTO;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.ModelAttribute;

@Data
public class RegistrationForm {
    private String username;
    private String password;
    private String confirm;
    private String fullname;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String phone;


    public TacoUser toUser(PasswordEncoder passwordEncoder){
        return new TacoUser(
                username, passwordEncoder.encode(password),fullname, city,state,phone
        );
    }
}
