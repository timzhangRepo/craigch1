package com.example.craigch1.DTO;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

@Data
@Table("TacoOrder")
public class TacoOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private Long id;
    private Date placedAt;

    @NotBlank
    @Column("customer_name")
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
