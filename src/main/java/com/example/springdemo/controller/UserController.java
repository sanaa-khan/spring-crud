package com.example.springdemo.controller;

import com.example.springdemo.model.MyResponse;
import com.example.springdemo.model.User;
import com.example.springdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URI;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/user_manager")
    public String userManager(Model model) {
        model.addAttribute("listUsers", userService.getAllUsers());
        model.addAttribute("usernamePlaceholder", "Username...");
        return "user_pages/user_manager";
    }

    @RequestMapping("/show_new_user_form")
    public String showNewUserForm(Model model) {
        // create model attribute to bind form data
        User user = new User();
        model.addAttribute("user", user);
        return "user_pages/new_user";
    }

    @GetMapping("/show_user_update_form/{id}")
    public String showUserUpdateForm(@PathVariable(value = "id") Integer user_id,
                                     Model model) {

        // get user object from service
        User user = userService.getById(user_id);

        // set user as a model attribute to pre-populate the form
        model.addAttribute("user", user);
        return "user_pages/update_user";
    }

    @RequestMapping("/search_user")
    public String searchUser(Model model,
                             @RequestParam(value = "search_username") String search_username) {

        if (search_username.isEmpty()) {
            model.asMap().put("listUsers", userService.getAllUsers());
            model.addAttribute("usernamePlaceholder", "Username...");
        } else {
            model.asMap().put("listUsers", userService.getByUsername(search_username));
            model.addAttribute("usernamePlaceholder", search_username);
        }

        return "user_pages/user_manager";
    }

    @RequestMapping("/delete_user/{id}")
    public String deleteUser(Model model,
                             @PathVariable(value = "id") Integer user_id,
                             HttpServletResponse response) throws IOException {
        userService.deleteById(user_id);
        response.sendRedirect("/user_manager");
        return null;
    }

    @PostMapping("/save_user")
    public String saveUser(@ModelAttribute("user") User user,
                           HttpServletResponse response) throws IOException {
        // save user to database
        userService.update(user);

        response.sendRedirect("/user_manager");
        return null;
    }

    @PostMapping("/addUser")
    public String addUser(@RequestParam("username") String username, @RequestParam("password") String password) {
        User user = new User(username, password);

        try {
            userService.insert(user);
        } catch (Exception e) {
            return "redirect:/error";
        }

        return "redirect:/user_manager";
    }


    @PostMapping("/update_user")
    public String updateUser(@ModelAttribute("user") User user,
                           HttpServletResponse response) throws IOException {
        // save user to database
        userService.update(user);

        response.sendRedirect("/user_manager");
        return null;
    }
}
