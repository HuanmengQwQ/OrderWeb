package com.restaurantorder.ordertest.service;

import com.restaurantorder.ordertest.entity.Order;

import java.util.List;

public interface OrderService {
    List<Order> getOrderListByUserName(String username);

    List<Order> searchOrderList(String orderName, String username);

    List<Order> getOrderList();
}
