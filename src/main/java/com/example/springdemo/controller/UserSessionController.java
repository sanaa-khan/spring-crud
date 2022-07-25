package com.example.springdemo.controller;

import com.example.springdemo.service.UserSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.sound.midi.Soundbank;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Controller
public class UserSessionController {

    @Autowired
    private UserSessionService userSessionService;

    @RequestMapping("/user_login_log_manager")
    public String userLoginLogManager(Model model) {
        model.addAttribute("listUserSessions", userSessionService.findAll());
        model.addAttribute("usernamePlaceholder", "Username...");
        return "user_log_pages/user_login_log_manager";
    }

    @RequestMapping("/search_user_sessions_by_username")
    public String searchUserSessionsByUsername(Model model,
                             @RequestParam(value = "search_username") String search_username) {

        System.out.println(search_username);

        if (search_username.isEmpty()) {
            model.asMap().put("listUserSessions", userSessionService.findAll());
            model.addAttribute("usernamePlaceholder", "Username...");
        } else {
            model.asMap().put("listUserSessions", userSessionService.findByUsername(search_username));
            model.addAttribute("usernamePlaceholder", search_username);
        }

        return "user_log_pages/user_login_log_manager";
    }


    @RequestMapping("/search_user_sessions_by_login_time")
    public String searchUserSessionsByLoginTime(Model model,
                                                @RequestParam(value = "search_datetime") String search_datetime) {

        System.out.println(search_datetime);
        // remove the T
        search_datetime = search_datetime.replace('T', ' ');
        // format date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        // convert to local time
        LocalDateTime dateTime = LocalDateTime.parse(search_datetime, formatter);
        // convert to timestamp
        Timestamp login_time = Timestamp.valueOf(dateTime);

        model.asMap().put("listUserSessions", userSessionService.findAllWithLoginTimeBefore(login_time));

        return "user_log_pages/user_login_log_manager";
    }
}
