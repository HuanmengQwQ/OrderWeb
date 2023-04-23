package com.restaurantorder.ordertest.dao;

import com.restaurantorder.ordertest.entity.Admin;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface AdminMapper {

    @Select("SELECT * FROM admin WHERE name = #{name} AND password = #{password}")
    Admin getAdmin(@Param("name")String name,@Param("password")String password);

    @Update("UPDATE note SET contain = #{text} WHERE n_id = 1")
    boolean changeNote(@Param("text")String contain_text);
}
