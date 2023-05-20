package com.restaurantorder.ordertest.filter;

import com.restaurantorder.ordertest.entity.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebFilter(urlPatterns = {"/userIndex", "/cart", "/addCart", "/changeCartFood", "/checkout", "/order", "/search"})
public class orderFilter extends HttpFilter {

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String url = request.getRequestURI();

        if(check(url)){
            HttpSession session = request.getSession();
            Cookie[] cookies = request.getCookies();
            User user = (User) session.getAttribute("user");

            if (user == null || cookies == null) {
                response.sendRedirect("login");
            }
        }
        chain.doFilter(request, response);
    }

    private boolean check(String url){
        if (!url.contains("/static/") || !url.contains("/orderStatic/")) {
            return true;
        } else return false;
    }
}
