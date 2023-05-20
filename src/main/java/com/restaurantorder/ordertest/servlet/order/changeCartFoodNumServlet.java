package com.restaurantorder.ordertest.servlet.order;

import com.restaurantorder.ordertest.entity.Cart;
import com.restaurantorder.ordertest.service.CartService;
import com.restaurantorder.ordertest.service.impl.CarServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/changeCartFood")
public class changeCartFoodNumServlet extends HttpServlet {
    CartService cartService;

    @Override
    public void init() throws ServletException {
        cartService = new CarServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int mid = Integer.parseInt(req.getParameter("mid"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));

        if (quantity > 0 && mid != 0) {
            //先定位是哪个菜
            Cart c = cartService.getCartFoodByMid(mid);

            //改变菜的数量
            c.setMquantity(quantity);

            //改变菜的总价
            c.setMtotalPrice(c.getMprice() * c.getMquantity());

            //最后写入数据库
            cartService.changeCartFood(c);

        } else {
            resp.sendError(500, "数值错误");
        }
    }
}
