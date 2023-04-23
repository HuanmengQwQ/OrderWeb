package com.restaurantorder.ordertest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Admin {
    private int aid;
    private String name;
    private String password;

    public Admin(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
