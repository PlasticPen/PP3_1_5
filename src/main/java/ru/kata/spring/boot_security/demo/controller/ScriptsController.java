package ru.kata.spring.boot_security.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/scripts")
public class ScriptsController {

    @GetMapping
    @RequestMapping("/navbar")
    public String navbar() {
        return "navbar";
    }

}