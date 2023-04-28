package com.restaurantorder.ordertest.servlet.mange;

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
import java.io.PrintWriter;

@WebServlet("/addMenu")
public class addMenuServlet extends HttpServlet {
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

        ThymeleafUtil.getEngine().process("addMenu.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        String m_name = req.getParameter("NameOfDish");
        double m_price = Double.parseDouble(req.getParameter("DishPrice"));
        String m_desc = req.getParameter("Introduction");

        System.out.println(m_name);

        boolean flag = adminService.addMenu(m_name, m_price, m_desc);


        if (flag) {
            out.write("{\"status\":\"success\",\"message\":\"添加成功！\"}");
        } else {
            out.write("{\"status\":\"fail\",\"error\":\"添加失败！\"}");
        }

    }
}
