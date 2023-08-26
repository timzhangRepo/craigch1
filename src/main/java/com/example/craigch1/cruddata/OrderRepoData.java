package com.example.craigch1.cruddata;

import com.example.craigch1.DTO.TacoOrder;
import org.springframework.core.annotation.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepoData extends CrudRepository<TacoOrder, Long> {
}
