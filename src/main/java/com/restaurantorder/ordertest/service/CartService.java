package com.restaurantorder.ordertest.service;

import com.restaurantorder.ordertest.entity.Cart;

import java.util.List;

public interface CartService {
    List<Cart> getCartList(String name);

    Cart getCartFoodByMid(int mid);

    void addCartFood(Cart cart);

    void changeCartFood(Cart cart);

    boolean delCartFood(String username, int mid);

    boolean clearCart(String username);

    boolean checkoutByname(String username);
}
