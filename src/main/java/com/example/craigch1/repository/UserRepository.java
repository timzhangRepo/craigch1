package com.example.craigch1.repository;


import com.example.craigch1.DTO.TacoUser;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<TacoUser, Long> {
    TacoUser findByUsername(String username);
}
