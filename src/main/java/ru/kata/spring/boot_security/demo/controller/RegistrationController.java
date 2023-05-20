package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
public class RegistrationController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    //After starting server if there are no users in DB, creates admin user with credentials: "admin" | "admin"
    @GetMapping
    public String initPage(@AuthenticationPrincipal User user) {
        if (user == null) {
            return "redirect:/createAdmin";
        } else if (user.getRoles().size() == 2) {
            return "redirect:/admin";
        }
        return "redirect:/user";
    }

    @GetMapping("/createAdmin")
    public String createAdmin() {
        userService.createAdminUser();
        return "redirect:/login";
    }
}
