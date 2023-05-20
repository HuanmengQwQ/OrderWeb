package com.restaurantorder.ordertest.dao;

import com.restaurantorder.ordertest.entity.Cart;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CartMapper {
    @Results({
            @Result(column = "uid", property = "uid"),
            @Result(column = "mid", property = "mid"),
            @Result(column = "Mname", property = "Mname"),
            @Result(column = "Mprice", property = "Mprice"),
            @Result(column = "Mquantity", property = "Mquantity"),
            @Result(column = "MtotalPrice", property = "MtotalPrice")
    })
    @Select("SELECT * FROM cart WHERE uid = (SELECT uid FROM user WHERE username = #{username})")
    List<Cart> getCartList(@Param("username") String username);

    @Results({
            @Result(column = "uid", property = "uid"),
            @Result(column = "mid", property = "mid"),
            @Result(column = "Mname", property = "Mname"),
            @Result(column = "Mprice", property = "Mprice"),
            @Result(column = "Mquantity", property = "Mquantity"),
            @Result(column = "MtotalPrice", property = "MtotalPrice")
    })
    @Insert("INSERT INTO cart value(null,#{uid},#{mid},#{Mname},#{Mprice},#{Mquantity},#{MtotalPrice})")
    void addCart(Cart cart);


    @Results({
            @Result(column = "uid", property = "uid"),
            @Result(column = "mid", property = "mid"),
            @Result(column = "Mname", property = "Mname"),
            @Result(column = "Mprice", property = "Mprice"),
            @Result(column = "Mquantity", property = "Mquantity"),
            @Result(column = "MtotalPrice", property = "MtotalPrice")
    })
    @Select("SELECT * FROM cart WHERE mid = #{mid}")
    Cart getCartFoodByMid(@Param("mid") int mid);

    @Results({
            @Result(column = "uid", property = "uid"),
            @Result(column = "mid", property = "mid"),
            @Result(column = "Mname", property = "Mname"),
            @Result(column = "Mprice", property = "Mprice"),
            @Result(column = "Mquantity", property = "Mquantity"),
            @Result(column = "Mtotalprice", property = "MtotalPrice")
    })
    @Update("UPDATE cart SET Mquantity = #{Mquantity},Mtotalprice = #{MtotalPrice} WHERE uid = #{uid} AND mid = #{mid}")
    void changeCartFood(Cart c);

    @Delete("DELETE FROM cart WHERE uid = (SELECT uid FROM user WHERE username = #{username}) AND mid = #{mid}")
    boolean delCartFood(@Param("username") String username, @Param("mid") int mid);

    @Delete("DELETE FROM cart WHERE uid = (SELECT uid FROM user WHERE username = #{username})")
    boolean clearCart(@Param("username") String username);


    @Insert("INSERT INTO od (uid,mid,Mname,Mnum,Msp,Date,Mstatus) " +
            "SELECT uid,mid,Mname,Mquantity,MtotalPrice,Now() AS Date,'准备中' AS Mstatus FROM cart " +
            "WHERE uid = (SELECT uid FROM user WHERE username = #{username});")
    boolean checkout(@Param("username") String username);
}
