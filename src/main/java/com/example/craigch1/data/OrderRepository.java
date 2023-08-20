package com.example.craigch1.data;

import com.example.craigch1.DTO.TacoOrder;

public interface OrderRepository {
    TacoOrder save(TacoOrder order);
}
