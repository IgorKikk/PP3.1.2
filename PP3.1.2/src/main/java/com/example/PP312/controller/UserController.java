package com.example.PP312.controller;


import com.example.PP312.model.User;
import com.example.PP312.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String showUser(Model model, Principal principal) {
        User user = userService.finedUserByUsername(principal.getName());
        model.addAttribute("user", user);
        return "/user/showUser";
    }
}
