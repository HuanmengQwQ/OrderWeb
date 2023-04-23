package com.restaurantorder.ordertest.filter;

import com.restaurantorder.ordertest.entity.Admin;
import com.restaurantorder.ordertest.entity.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebFilter(urlPatterns = "/order")
public class orderFilter extends HttpFilter {

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String url = request.getRequestURI();

        if(check(url)){
            HttpSession session = request.getSession();
            Cookie[] cookies = request.getCookies();
            Admin admin = (Admin) session.getAttribute("Admin");

            if (admin == null || cookies ==null){
                response.sendRedirect("login");
            }
        }
        chain.doFilter(request, response);
    }

    private boolean check(String url){
        if (!url.contains("static/")){
            return true;
        }
        else return false;
    }
}
