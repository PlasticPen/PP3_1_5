package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

//    @GetMapping
//    public String showAllUsers(@AuthenticationPrincipal User user, Model model) {
//        model.addAttribute("users", userService.allUsers());
//        model.addAttribute("currentUser", user);
//        model.addAttribute("newUser", new User());
//        return "admin";
//    }

    @GetMapping
    public String showAllUsers(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("users", userService.allUsers());
        model.addAttribute("currentUser", user);
        model.addAttribute("newUser", new User());
//        return "showAllUsers";
        return "adminPage";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "new";
    }

    @PostMapping
    public String create(@ModelAttribute("newUser") User newUser) {
        userService.saveUser(newUser);
        return "redirect:/admin";
    }

    //If admin deletes itself and there are no users, redirect to login page. Restarting server will create default admin user
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id, @AuthenticationPrincipal User user) {
        System.out.println("ID = " + id);
        System.out.println(user.getId());
        userService.deleteUser(id);
        if (userService.findUserById(user.getId()).getId() == 0) {
            return "redirect:/";
        }
        return "redirect:/admin";
    }

//    @GetMapping("/{id}/edit")
//    public String edit(Model model, @PathVariable("id") long id) {
//        model.addAttribute("oldUser", userService.findUserById(id));
//        return "edit";
//    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("newUser") User updatedUser, @PathVariable("id") long id) {
        userService.updateUser(id, updatedUser);
        return "redirect:/admin";
    }
}
