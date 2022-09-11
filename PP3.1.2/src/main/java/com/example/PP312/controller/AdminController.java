package com.example.PP312.controller;

import com.example.PP312.model.User;
import com.example.PP312.service.RoleService;
import com.example.PP312.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@Controller
@RequestMapping("/admin/")
public class AdminController {
    private UserService userService;
    private RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String helloPage() {
        return "/admin/index";
    }

    @GetMapping("/{id}")
    public String showUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "/admin/showUser";
    }

    @GetMapping("/allusers")
    public String getAllUsers(Model model) {
        List<User> userList = userService.getAllUsers();
        model.addAttribute("users", userList);
        return "/admin/allusers";
    }
    @GetMapping("/adduser")
    public String addUser(Model model) {
        model.addAttribute("allRoleList", roleService.getAllRoles());
        model.addAttribute("user", new User());
        return "/admin/adduser";
    }
    @PostMapping("/adduser")
    public String add(@ModelAttribute("user") User user){
        userService.createUser(user);
        return "redirect:/admin/allusers";
    }
    @GetMapping("/{id}/updateuser")
    public String updateUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        model.addAttribute("roleList", roleService.getAllRoles());
        return "/admin/updateuser";
    }
    @PatchMapping("/updateuser")
    public String update(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/admin/allusers";
    }
    @DeleteMapping("/{id}/deleteuser")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin/allusers";
    }
}
