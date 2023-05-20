package com.restaurantorder.ordertest.dao;

import com.restaurantorder.ordertest.entity.Order;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrderMapper {
    @Results({
            @Result(column = "uid", property = "uid"),
            @Result(column = "mid", property = "mid"),
            @Result(column = "Mname", property = "Mname"),
            @Result(column = "Mnum", property = "Mnum"),
            @Result(column = "Msp", property = "Msp"),
            @Result(column = "Date", property = "Date"),
            @Result(column = "Mstatus", property = "Mstatus")
    })
    @Select("SELECT uid,mid,Mname,Mnum,Msp,Date,Mstatus FROM od " +
            "WHERE uid = " +
            "(SELECT uid FROM user WHERE username = #{username})")
    List<Order> getOrderListByUserName(@Param("username") String username);

    @Results({
            @Result(column = "uid", property = "uid"),
            @Result(column = "mid", property = "mid"),
            @Result(column = "Mname", property = "Mname"),
            @Result(column = "Mname", property = "Mname"),
            @Result(column = "Mnum", property = "Mnum"),
            @Result(column = "Msp", property = "Msp"),
            @Result(column = "Date", property = "Date"),
            @Result(column = "Mstatus", property = "Mstatus")
    })
    @Select("SELECT uid,mid,Mname,Mnum,Msp,Date,Mstatus FROM od " +
            "WHERE Mname LIKE CONCAT('%', #{menuname}, '%') " +
            "AND uid = (SELECT uid FROM user WHERE username = #{username})")
    List<Order> searchOrderListByUserName(@Param("menuname") String menuname, @Param("username") String username);

    @Select("SELECT uid,mid,Mname,Mnum,Msp,Date,Mstatus FROM od")
    List<Order> getOrderList();
}
