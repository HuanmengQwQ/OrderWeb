package com.restaurantorder.ordertest.servlet.order;

import com.restaurantorder.ordertest.service.CartService;
import com.restaurantorder.ordertest.service.impl.CarServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/pay")
public class payServlet extends HttpServlet {
    CartService cartService;

    @Override
    public void init() throws ServletException {
        cartService = new CarServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");

        if (cartService.checkoutByname(username)) {
            resp.sendRedirect("order");
        } else {
            resp.sendError(500);
        }
    }
}
