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

    @GetMapping
    @RequestMapping("/adminPanel")
    public String adminPanel() {
        return "adminPanel";
    }

    @GetMapping
    @RequestMapping("/allUsers")
    public String allUsers() {
        return "allUsers";
    }

    @GetMapping
    @RequestMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping
    @RequestMapping("/new")
    public String newUser() {
        return "new";
    }

    @GetMapping
    @RequestMapping("/aboutUser")
    public String aboutUser() {
        return "aboutUser";
    }

    @GetMapping
    @RequestMapping("/userInformationPage")
    public String userInformationPage() {
        return "userInformationPage";
    }
}
