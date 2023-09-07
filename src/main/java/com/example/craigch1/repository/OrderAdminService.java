package com.example.craigch1.repository;


import com.example.craigch1.DTO.TacoOrder;
import org.springframework.data.repository.CrudRepository;

public interface OrderAdminService extends CrudRepository<TacoOrder, String> {}
