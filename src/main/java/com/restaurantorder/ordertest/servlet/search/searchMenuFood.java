package com.restaurantorder.ordertest.servlet.search;

import com.restaurantorder.ordertest.entity.Food;
import com.restaurantorder.ordertest.service.FoodService;
import com.restaurantorder.ordertest.service.impl.FoodServiceImpl;
import com.restaurantorder.ordertest.util.ThymeleafUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.thymeleaf.context.Context;

import java.io.IOException;
import java.util.List;

@WebServlet("/search")
public class searchMenuFood extends HttpServlet {
    FoodService foodService;

    @Override
    public void init() throws ServletException {
        foodService = new FoodServiceImpl();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        Context context = new Context();
        String foodName = req.getParameter("foodName");

        List<Food> foodList = foodService.getMenuFood(foodName);

        String name = (String) session.getAttribute("username");
        context.setVariable("username", name);
        context.setVariable("searchFoodList", foodList);
        ThymeleafUtil.getEngine().process("searchMenuFoodList.html", context, resp.getWriter());
    }
}
