package com.example.craigch1.controller;

import com.example.craigch1.DTO.TacoOrder;
import com.example.craigch1.DTO.TacoUser;
import com.example.craigch1.repository.OrderRepository;
import com.example.craigch1.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.awt.print.Pageable;
import java.security.Principal;

@Controller
@Slf4j
@SessionAttributes("tacoOrder")
@RequestMapping("/orders")
public class OrderController {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public OrderController(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    Logger logger = LoggerFactory.getLogger(OrderController.class);
    @GetMapping("/current")
    public String orderForm(Model model, @ModelAttribute("tacoOrder") TacoOrder tacoOrder, SessionStatus sessionStatus){
        return "orderForm";
    }
    @PostMapping
    public String processOrder(@Valid TacoOrder order, Errors errors, SessionStatus sessionStatus, @AuthenticationPrincipal TacoUser tacoUser){
        if(errors.hasErrors()){
            //如果在验证TacoOrder对象时发现了任何错误，这些错误都会被添加到这个Errors对象中
            return "orderForm";
        }
        logger.info("processOrder {}", tacoUser);
        order.setUser(tacoUser);
        orderRepository.save(order);
        log.info("Order submitted: {}", order);
        sessionStatus.setComplete();
        return "redirect:/";
    }

}
