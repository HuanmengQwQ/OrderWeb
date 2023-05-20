package com.restaurantorder.ordertest.dao;

import com.restaurantorder.ordertest.entity.Food;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface FoodMapper {

    @Select("SELECT * FROM menu")
    List<Food> getFoodList();

    @Select("SELECT * FROM menu WHERE m_id = #{mid}")
    Food getFoodById(@Param("mid") Integer mid);

    @Select("SELECT * FROM menu WHERE Mname LIKE CONCAT('%', #{menuname}, '%')")
    List<Food> getMenuFoodByName(@Param("menuname") String menuname);
}
