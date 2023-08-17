package com.example.craigch1.controller;

import com.example.craigch1.DTO.TacoOrder;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@Slf4j
@SessionAttributes("tacoOrder")
@RequestMapping("/orders")
public class OrderController {

    Logger logger = LoggerFactory.getLogger(OrderController.class);
    @GetMapping("/current")
    public String orderForm(Model model, @ModelAttribute("tacoOrder") TacoOrder tacoOrder, SessionStatus sessionStatus){
        return "orderForm";
    }
    @PostMapping
    public String processOrder(TacoOrder order, SessionStatus sessionStatus){
        log.info("Order submitted: {}", order);
        sessionStatus.setComplete();
        return "redirect:/";
    }
}
