package com.example.craigch1.repository;

import com.example.craigch1.DTO.TacoOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<TacoOrder, String> {
}
