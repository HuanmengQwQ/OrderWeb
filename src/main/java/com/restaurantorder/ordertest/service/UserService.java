package com.restaurantorder.ordertest.service;

import jakarta.servlet.http.HttpSession;

public interface UserService {
    boolean auth(String username, String password, HttpSession session);

    String getNoteContain();
}
