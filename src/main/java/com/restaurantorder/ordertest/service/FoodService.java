package com.restaurantorder.ordertest.service;

import com.restaurantorder.ordertest.entity.Food;

import java.util.List;

public interface FoodService {
    List<Food> getFoodList();

    Food getFoodById(Integer mid);

    List<Food> getMenuFood(String menuname);
}
