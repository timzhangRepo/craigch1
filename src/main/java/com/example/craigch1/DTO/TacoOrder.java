package com.example.craigch1.DTO;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

@Data
public class TacoOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Date placedAt;

    @NotBlank
    private String deliveryName;
    private String deliveryStreet;
    private String deliveryCity;
    private String deliveryState;
    private String deliveryZip;

    private String ccNumber;


    private String ccExpiration;

    private String ccCVV;

    private List<Taco> tacos = new ArrayList<>();

    public void addTaco(Taco taco){
        tacos.add(taco);
    }
}
