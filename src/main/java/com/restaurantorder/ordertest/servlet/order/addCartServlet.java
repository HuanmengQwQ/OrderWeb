package com.restaurantorder.ordertest.servlet.order;

import com.restaurantorder.ordertest.entity.Cart;
import com.restaurantorder.ordertest.entity.Food;
import com.restaurantorder.ordertest.entity.User;
import com.restaurantorder.ordertest.service.CartService;
import com.restaurantorder.ordertest.service.FoodService;
import com.restaurantorder.ordertest.service.UserService;
import com.restaurantorder.ordertest.service.impl.CarServiceImpl;
import com.restaurantorder.ordertest.service.impl.FoodServiceImpl;
import com.restaurantorder.ordertest.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/addCart")
public class addCartServlet extends HttpServlet {
    CartService cartService;
    FoodService foodService;
    UserService userService;

    @Override
    public void init() throws ServletException {
        cartService = new CarServiceImpl();
        foodService = new FoodServiceImpl();
        userService = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String mid = req.getParameter("mid");
        String username = (String) session.getAttribute("username");

        //得到相应的菜
        Food f = foodService.getFoodById(Integer.valueOf(mid));
        //得到相应的人
        User u = userService.getUserByName(username);
        //得到购物车中的食物对象
        Cart cartFood = cartService.getCartFoodByMid(Integer.parseInt(mid));

        if (cartFood == null) {
            //不存在创建购物车单个对象
            cartFood = new Cart();

            cartFood.setUid(u.getId());
            cartFood.setMid(f.getM_id());
            cartFood.setMname(f.getMname());
            cartFood.setMquantity(1);
            cartFood.setMprice(f.getMprice());
            cartFood.setMtotalPrice(f.getMprice());

            //添加
            cartService.addCartFood(cartFood);
        } else {
            cartFood.setUid(u.getId());
            cartFood.setMid(f.getM_id());
            cartFood.setMname(f.getMname());
            cartFood.setMprice(f.getMprice());

            int c = cartFood.getMquantity();
            int n = c + 1;

            cartFood.setMquantity(n);
            cartFood.setMtotalPrice(cartFood.getMtotalPrice() + cartFood.getMprice());

            cartService.changeCartFood(cartFood);
        }

        resp.sendRedirect("cart");
    }
}
