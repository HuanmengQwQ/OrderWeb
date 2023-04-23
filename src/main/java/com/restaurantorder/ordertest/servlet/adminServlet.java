package com.restaurantorder.ordertest.servlet;

import com.restaurantorder.ordertest.service.AdminService;
import com.restaurantorder.ordertest.service.impl.AdminServiceImpl;
import com.restaurantorder.ordertest.util.ThymeleafUtil;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.context.Context;

import java.io.IOException;

@WebServlet("/admin")
public class adminServlet extends HttpServlet {
    AdminService adminService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        adminService = new AdminServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context = new Context();
        if(req.getSession().getAttribute("login-fail") != null){
            context.setVariable("fail",true);
        }
        ThymeleafUtil.getEngine().process("adminLogin.html",context,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String remember = req.getParameter("remember-me");

        if(adminService.auth(name,password,req.getSession())){
            //设置cookies
            if (remember.equals("on")){
                Cookie cookie_username = new Cookie("name", name);
                cookie_username.setMaxAge(7*24*60*60);
                Cookie cookie_password = new Cookie("password", password);
                cookie_password.setMaxAge(7*24*60*60);
                resp.addCookie(cookie_username);
                resp.addCookie(cookie_password);
            }
        }else {
            req.getSession().setAttribute("login-fail",new Object());
            this.doGet(req,resp);
        }


    }
}
