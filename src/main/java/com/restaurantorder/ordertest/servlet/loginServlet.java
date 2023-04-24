package com.restaurantorder.ordertest.servlet;

import com.restaurantorder.ordertest.service.UserService;
import com.restaurantorder.ordertest.service.impl.UserServiceImpl;
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

@WebServlet("/login")
public class loginServlet extends HttpServlet {

    UserService userService;
    @Override
    public void init(ServletConfig config) throws ServletException {
        userService = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context = new Context();
        if (req.getSession().getAttribute("user-login-fail") != null) {
            context.setVariable("userFail", true);
        }
        ThymeleafUtil.getEngine().process("login.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("Password");
        String remember = req.getParameter("remember-me");

        if (userService.auth(username, password, req.getSession())) {
            //设置cookies
            if (!remember.isEmpty()) {
                Cookie cookie_username = new Cookie("username", username);
                cookie_username.setMaxAge(7 * 24 * 60 * 60);
                Cookie cookie_password = new Cookie("password", password);
                cookie_password.setMaxAge(7 * 24 * 60 * 60);
                resp.addCookie(cookie_username);
                resp.addCookie(cookie_password);


            }
            resp.getWriter().println("登陆成功");
        } else {
            req.getSession().setAttribute("user-login-fail", new Object());
            this.doGet(req, resp);
        }
    }
}
