package com.example.springdemo.model;

import java.sql.Timestamp;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSession {

    // primary key of the table
    private String id;

    private String username;

    private Timestamp login_time;

    private String login_ip;

    private String session_id;

    private Timestamp logout_time;

    // user id (same as in User)
    private Integer user_id;

    public UserSession(String id, String username, Timestamp login_time, String login_ip, String session_id, Timestamp logout_time, Integer user_id) {
        this.id = id;
        this.username = username;
        this.login_time = login_time;
        this.login_ip = login_ip;
        this.session_id = session_id;
        this.logout_time = logout_time;
        this.user_id = user_id;
    }
}
