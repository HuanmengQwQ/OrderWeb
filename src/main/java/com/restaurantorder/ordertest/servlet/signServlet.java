package com.restaurantorder.ordertest.servlet;

import com.restaurantorder.ordertest.service.SignService;
import com.restaurantorder.ordertest.service.UserService;
import com.restaurantorder.ordertest.service.impl.SignServiceImpl;
import com.restaurantorder.ordertest.util.ThymeleafUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.context.Context;

import java.io.IOException;

@WebServlet("/sign")
public class signServlet extends HttpServlet {
    SignService signService;

    @Override
    public void init() throws ServletException {
        signService = new SignServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context = new Context();
        if(req.getSession().getAttribute("sign-success") != null){
            context.setVariable("msg",req.getSession().getAttribute("sign-success"));
        } else if (req.getSession().getAttribute("sign-fail") != null) {
            context.setVariable("msg",req.getSession().getAttribute("sign-fail"));
        }else {
            context.setVariable("msg",null);
        }
        ThymeleafUtil.getEngine().process("sign.html",context,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String repeatpassword = req.getParameter("repeatpassword");

        boolean flge = signService.check(username,password,repeatpassword);

        if(flge){
            req.getSession().setAttribute("sign-success","恭喜注册成功！");
            this.doGet(req,resp);
        }else {
            req.getSession().setAttribute("sign-fail","注册失败！请检查密码是否一致");
        }
    }
}
