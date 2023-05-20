package com.restaurantorder.ordertest.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@Getter
@Setter
public class Order {
    private int uid;
    private int mid;
    private String Mname;
    private int Mnum;
    private double Msp; //菜的总价
    private Date Date;
    private String Mstatus; //订单状态

    public String getDateString() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return dateFormat.format(this.Date);
    }
}
