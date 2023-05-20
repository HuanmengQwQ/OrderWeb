package com.restaurantorder.ordertest.servlet.page;

import com.restaurantorder.ordertest.entity.Cart;
import com.restaurantorder.ordertest.service.CartService;
import com.restaurantorder.ordertest.service.UserService;
import com.restaurantorder.ordertest.service.impl.CarServiceImpl;
import com.restaurantorder.ordertest.service.impl.UserServiceImpl;
import com.restaurantorder.ordertest.util.ThymeleafUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.thymeleaf.context.Context;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

@WebServlet("/cart")
public class cartServlet extends HttpServlet {
    CartService cartService;
    UserService userService;

    double cartTotalPrice;

    @Override
    public void init() throws ServletException {
        cartService = new CarServiceImpl();
        userService = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        cartTotalPrice = 0;
        Context context = new Context();
        HttpSession session = req.getSession();
        String name = (String) session.getAttribute("username");
        List<Cart> cartList = cartService.getCartList(name);

        for (Cart c :
                cartList) {
            cartTotalPrice += c.getMtotalPrice();
        }

        DecimalFormat df = new DecimalFormat("#0.00");//保留两位小数

        context.setVariable("username", name);
        context.setVariable("cartList", cartList);
        context.setVariable("totalPrice", df.format(cartTotalPrice));
        ThymeleafUtil.getEngine().process("cart.html", context, resp.getWriter());
    }
}
