package com.restaurantorder.ordertest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Food {
    private int m_id;
    private String Mname;
    private double Mprice;
    private String Mintrodution;

    public Food(String mname, double mprice, String mintrodution) {
        Mname = mname;
        Mprice = mprice;
        Mintrodution = mintrodution;
    }
}
