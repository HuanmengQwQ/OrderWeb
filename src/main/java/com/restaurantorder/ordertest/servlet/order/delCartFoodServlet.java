package com.restaurantorder.ordertest.servlet.order;

import com.restaurantorder.ordertest.service.CartService;
import com.restaurantorder.ordertest.service.impl.CarServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/delCarFood")
public class delCartFoodServlet extends HttpServlet {
    CartService cartService;

    @Override
    public void init() throws ServletException {
        cartService = new CarServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = (String) req.getSession().getAttribute("username");

        String m = req.getParameter("mid");

        //防止请求出现空
        if (m != null) {
            int mid = Integer.parseInt(m);

            cartService.delCartFood(username, mid);
        }
        resp.sendRedirect("cart");
    }
}
