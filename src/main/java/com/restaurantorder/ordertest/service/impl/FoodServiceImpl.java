package com.restaurantorder.ordertest.service.impl;

import com.restaurantorder.ordertest.dao.FoodMapper;
import com.restaurantorder.ordertest.entity.Food;
import com.restaurantorder.ordertest.service.FoodService;
import com.restaurantorder.ordertest.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class FoodServiceImpl implements FoodService {

    @Override
    public List<Food> getFoodList() {
        try (SqlSession sqlSession = MybatisUtil.getSession()) {
            FoodMapper foodMapper = sqlSession.getMapper(FoodMapper.class);
            return foodMapper.getFoodList();
        }
    }

    @Override
    public Food getFoodById(Integer mid) {
        try (SqlSession sqlSession = MybatisUtil.getSession()) {
            FoodMapper foodMapper = sqlSession.getMapper(FoodMapper.class);
            return foodMapper.getFoodById(mid);
        }
    }

    @Override
    public List<Food> getMenuFood(String menuname) {
        try (SqlSession sqlSession = MybatisUtil.getSession()) {
            FoodMapper foodMapper = sqlSession.getMapper(FoodMapper.class);

            return foodMapper.getMenuFoodByName(menuname);
        }
    }
}
