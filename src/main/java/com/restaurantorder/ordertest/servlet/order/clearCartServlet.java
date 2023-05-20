package com.restaurantorder.ordertest.servlet.order;

import com.restaurantorder.ordertest.service.CartService;
import com.restaurantorder.ordertest.service.impl.CarServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/clearCart")
public class clearCartServlet extends HttpServlet {
    CartService cartService;

    @Override
    public void init() throws ServletException {
        cartService = new CarServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");

        boolean flag = cartService.clearCart(username);

        if (flag && username != null) {
            resp.setStatus(200);
            resp.getWriter().write("OK");
        } else {
            resp.sendError(500, "用户名为空！");
        }
    }
}
