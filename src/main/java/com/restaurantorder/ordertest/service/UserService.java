package com.restaurantorder.ordertest.service;

import com.restaurantorder.ordertest.entity.User;
import jakarta.servlet.http.HttpSession;

public interface UserService {
    boolean auth(String username, String password, HttpSession session);

    String getNoteContain();

    User getUserByName(String name);
}
