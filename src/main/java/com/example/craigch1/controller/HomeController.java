package com.example.craigch1.controller;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomeController  {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
    @GetMapping("/")
    public String home(){
        logger.info("Accessing home method in HomeController");
        System.out.println("Hello World");
        return "home";
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        logger.info("Hello method called");
        return ResponseEntity.ok("Hello, World!");
    }
}