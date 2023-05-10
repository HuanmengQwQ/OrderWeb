package com.restaurantorder.ordertest.service;

import com.restaurantorder.ordertest.entity.User;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public interface AdminService {
    boolean auth(String name, String password, HttpSession session);

    boolean ChangeAnnouncements(String note);

    boolean addMenu(String m_name, double price, String desc);

    boolean delMenu(String id);

    Double getSumPrice();

    Integer getSumOrder();

    Integer getSumCustomer();

    List<User> getUserList();
}
