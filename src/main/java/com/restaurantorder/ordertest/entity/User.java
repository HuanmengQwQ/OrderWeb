package com.restaurantorder.ordertest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class User {
    private int id;
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
