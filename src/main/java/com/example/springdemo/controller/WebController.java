package com.example.springdemo.controller;

import com.example.springdemo.model.MyResponse;
import com.example.springdemo.model.User;
import com.example.springdemo.model.UserSession;
import com.example.springdemo.service.UserService;

import com.example.springdemo.service.UserSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.UUID;

@Controller
public class WebController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserSessionService userSessionService;

    private UserSession currentUserSession;

    @RequestMapping("/")
    public String index(HttpSession session) {
        //return "index";
        return "redirect:/user_manager";
    }

    @GetMapping("/login")
    public String login_page(HttpSession session) {
        return "login";
    }

    @PostMapping("/register")
    public String register(
            @RequestParam(value = "signup_username") String signup_username,
            @RequestParam(value = "signup_password") String signup_password,
            Model model,
            HttpSession session,
            HttpServletResponse response
    )   throws IOException {


        // set error on login page for blank fields
        if (signup_username.isEmpty() || signup_password.isEmpty()) {
            model.addAttribute("inputBlank", true);
            return "login";
        }

        // check if user by that username already exists
        User user = userService.getByUsername(signup_username);
        if (user != null) {
            model.addAttribute("usernameExists", true);
            return "login";
        }

        // create new user with provided values
        user = new User(signup_username, signup_password);
        userService.insert(user);

        // pass message to login page
        model.addAttribute("registrationSuccessful", true);
        return "login";
    }

    // accept post request for login url
    @PostMapping("/login")
    public String login(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password,
            Model model,
            HttpSession session,
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {

        // get the user object
        User user = userService.getByUsername(username);

        // authorise with password
        if (password.equals(user.getPassword())) {

            // add current login login log
            String newID = UUID.randomUUID().toString();                                                                    // generate a random ID
            Timestamp current_time = new Timestamp(System.currentTimeMillis());                                             // get current time
            currentUserSession = new UserSession(newID, user.getUsername(), current_time, request.getRemoteAddr(), session.getId(), null, user.getUser_id());
            userSessionService.insert(currentUserSession);                                                                  // save login log to database

            // set relevant session attributes
            session.setAttribute("isLoggedIn", true);
            session.setAttribute("username", username);

            // redirect to homepage page
            response.sendRedirect("/");

            return null;
        }

        // set attribute for invalid credential error
        else {
            model.addAttribute("error", true);
            return "login";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session, HttpServletResponse response) throws IOException {
        // update login log with logout time
        Timestamp current_time = new Timestamp(System.currentTimeMillis());
        userSessionService.updateLogoutTime(currentUserSession.getId(), current_time);

        // invalidate current session and return to index page
        currentUserSession = null;
        session.invalidate();
        response.sendRedirect("/login");
        return null;
    }
}
