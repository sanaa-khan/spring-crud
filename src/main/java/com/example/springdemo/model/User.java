package com.example.springdemo.model;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.ToString;

@Entity
@ToString
public class User {

    private Integer user_id;

    private String username;

    private String password;

    public User() {
        user_id = 0;
        username = "";
        password = "";
    }

    public User(int id, String username, String password) {
        this.user_id = id;
        this.username = username;
        this.password = password;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
