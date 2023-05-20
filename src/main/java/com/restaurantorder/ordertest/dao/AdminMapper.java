package com.restaurantorder.ordertest.dao;

import com.restaurantorder.ordertest.entity.Admin;
import com.restaurantorder.ordertest.entity.Food;
import com.restaurantorder.ordertest.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface AdminMapper {

    @Select("SELECT * FROM admin WHERE name = #{name} AND password = #{password}")
    Admin getAdmin(@Param("name") String name, @Param("password") String password);

    @Update("UPDATE note SET contain = #{text} WHERE n_id = 1")
    boolean changeNote(@Param("text") String contain_text);

    @Insert("INSERT INTO menu VALUE(#{m_id},#{Mname},#{Mprice},#{Mintrodution})")
    void addMenu(Food f);

    @Select("SELECT * FROM menu WHERE Mname = #{m_name}")
    Food checkMenu(@Param("m_name") String name);

    @Delete("DELETE FROM menu WHERE m_id = #{m_id}")
    int delMenu(@Param("m_id") String id);

    @Results({
            @Result(column = "m_id", property = "m_id"),
            @Result(column = "Mname", property = "Mname"),
            @Result(column = "Mprice", property = "Mprice"),
            @Result(column = "Mintrodution", property = "Mintrodution")
    })
    @Select("SELECT * FROM menu")
    List<Food> getFoodList();

    @Select("SELECT SUM(msp) FROM od")
    Double getSumPrice();

    @Select("SELECT COUNT(*) FROM od")
    Integer getSumOrder();

    @Select("SELECT COUNT(DISTINCT uid) FROM od GROUP BY uid")
    Integer getSumCustomer();

    @Results({
            @Result(column = "uid", property = "id"),
            @Result(column = "username", property = "username")
    })
    @Select("Select uid,username FROM user")
    List<User> getUserList();
}
