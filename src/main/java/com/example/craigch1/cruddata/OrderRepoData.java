package com.example.craigch1.cruddata;

import com.example.craigch1.DTO.TacoOrder;
import org.springframework.core.annotation.Order;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public interface OrderRepoData extends CrudRepository<TacoOrder, Long> {
    List<TacoOrder> findTacoOrderByDeliveryCity(String deliveryCity);

}
