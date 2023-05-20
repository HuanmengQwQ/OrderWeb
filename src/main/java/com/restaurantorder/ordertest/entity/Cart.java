package com.restaurantorder.ordertest.entity;

import lombok.Data;

@Data
public class Cart {
    private int uid;
    private int mid;
    private String Mname;
    private double Mprice;//单价
    private int Mquantity;//数量
    private double MtotalPrice;//单件商品总价
}
