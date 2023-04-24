package com.restaurantorder.ordertest.dao;

import com.restaurantorder.ordertest.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    @Select("SELECT * FROM user WHERE username = #{username} AND password = #{password}")
    User getUser(@Param("username") String username, @Param("password") String password);

    @Insert("INSERT INTO user VALUE(#{id},#{username},#{password})")
    int setUser(User user);

    @Select("SELECT contain FROM note WHERE n_id = 1")
    String getNote();
}
