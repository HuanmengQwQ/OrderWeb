package com.restaurantorder.ordertest.servlet.page;

import com.restaurantorder.ordertest.service.AdminService;
import com.restaurantorder.ordertest.service.impl.AdminServiceImpl;
import com.restaurantorder.ordertest.util.ThymeleafUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.thymeleaf.context.Context;

import java.io.IOException;

@WebServlet("/manageMenu")
public class manageMenuServlet extends HttpServlet {
    AdminService adminService;

    @Override
    public void init() throws ServletException {
        adminService = new AdminServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context = new Context();
        HttpSession session = req.getSession();
        String name = (String) session.getAttribute("name");
        context.setVariable("name", name);
        ThymeleafUtil.getEngine().process("managingMenu.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
