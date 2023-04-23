package com.restaurantorder.ordertest.service;

import jakarta.servlet.http.HttpSession;

public interface AdminService {
    boolean auth(String name, String password, HttpSession session);
}
