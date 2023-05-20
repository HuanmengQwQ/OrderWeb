package com.restaurantorder.ordertest.servlet.page;

import com.restaurantorder.ordertest.entity.Cart;
import com.restaurantorder.ordertest.service.CartService;
import com.restaurantorder.ordertest.service.impl.CarServiceImpl;
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

@WebServlet("/checkout")
public class checkoutServlet extends HttpServlet {
    CartService cartService;
    double cartTotalPrice;

    @Override
    public void init() throws ServletException {
        cartService = new CarServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        cartTotalPrice = 0;

        Context context = new Context();

        HttpSession session = req.getSession();
        String name = (String) session.getAttribute("username");
        List<Cart> c = cartService.getCartList(name);

        //计算总价
        for (Cart ca :
                c) {
            cartTotalPrice += ca.getMtotalPrice();
        }

        context.setVariable("username", name);
        context.setVariable("checkOutCartList", c);
        context.setVariable("cartListTotalPrice", cartTotalPrice);

        ThymeleafUtil.getEngine().process("checkout.html", context, resp.getWriter());
    }
}
