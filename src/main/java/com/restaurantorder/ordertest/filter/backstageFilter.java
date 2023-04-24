package com.restaurantorder.ordertest.filter;

import com.restaurantorder.ordertest.entity.Admin;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebFilter(urlPatterns = {"/adminIndex"})
public class backstageFilter extends HttpFilter {
    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String url = req.getRequestURI();

        if(check(url)){
            HttpSession session = req.getSession();
            Cookie[] cookies = req.getCookies();
            Admin user = (Admin) session.getAttribute("admin");

            if (user == null || cookies ==null){
                res.sendRedirect("admin");
            }
        }

        chain.doFilter(req,res);
    }
    private boolean check(String url){
        if (!url.contains("/static/")) {
            return true;
        } else return false;
    }
}
