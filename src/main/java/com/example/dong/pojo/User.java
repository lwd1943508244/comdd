package com.example.dong.pojo;

import lombok.Data;

@Data
public class User {
    private Integer uid;
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
