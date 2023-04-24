package com.restaurantorder.ordertest.servlet;

import com.restaurantorder.ordertest.service.UserService;
import com.restaurantorder.ordertest.service.impl.UserServiceImpl;
import com.restaurantorder.ordertest.util.ThymeleafUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.context.Context;

import java.io.IOException;

@WebServlet("/home")
public class homeServlet extends HttpServlet {
    UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context = new Context();
        String note = userService.getNoteContain();
        context.setVariable("note", note);
        ThymeleafUtil.getEngine().process("home.html", context, resp.getWriter());
    }

}
