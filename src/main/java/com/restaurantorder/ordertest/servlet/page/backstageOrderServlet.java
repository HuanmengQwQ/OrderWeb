package com.restaurantorder.ordertest.servlet.page;

import com.restaurantorder.ordertest.service.OrderService;
import com.restaurantorder.ordertest.service.impl.OrderServiceImpl;
import com.restaurantorder.ordertest.util.ThymeleafUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.thymeleaf.context.Context;

import java.io.IOException;

@WebServlet("/orders")
public class backstageOrderServlet extends HttpServlet {
    OrderService orderService;

    @Override
    public void init() throws ServletException {
        orderService = new OrderServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context = new Context();

        HttpSession session = req.getSession();
        String name = (String) session.getAttribute("name");
        context.setVariable("name", name);
        context.setVariable("customerOrders", orderService.getOrderList());

        ThymeleafUtil.getEngine().process("orders.html", context, resp.getWriter());
    }
}
