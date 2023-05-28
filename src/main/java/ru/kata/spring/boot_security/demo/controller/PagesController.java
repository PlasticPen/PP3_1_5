package ru.kata.spring.boot_security.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pages")
public class PagesController {

    @GetMapping
    @RequestMapping("/navbar")
    public String navbar() {
        return "navbar";
    }

    @GetMapping
    @RequestMapping("/editUser")
    public String editUser() {
        return "editUser";
    }

    @GetMapping
    @RequestMapping("/deleteUser")
    public String deleteUser() {
        return "deleteUser";
    }
}
