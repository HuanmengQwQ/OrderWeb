package com.restaurantorder.ordertest.servlet.mange;

import com.restaurantorder.ordertest.service.AdminService;
import com.restaurantorder.ordertest.service.impl.AdminServiceImpl;
import com.restaurantorder.ordertest.util.ThymeleafUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.apache.commons.io.IOUtils;
import org.thymeleaf.context.Context;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

@MultipartConfig
@WebServlet("/addMenu")
public class addMenuServlet extends HttpServlet {
    AdminService adminService;

    @Override
    public void init() throws ServletException {
        adminService = new AdminServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context = new Context();

        HttpSession session = req.getSession();
        String name = (String) session.getAttribute("name");
        context.setVariable("name", name);

        ThymeleafUtil.getEngine().process("addMenu.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        String m_name = req.getParameter("NameOfDish");
        double m_price = Double.parseDouble(req.getParameter("DishPrice"));
        String m_desc = req.getParameter("Introduction");
        Part filePart = req.getPart("DishImg");

        if (m_name != null && filePart != null) {
            try (FileOutputStream stream = new FileOutputStream("src/main/webapp/static/images/menuImages/" + m_name + ".jpg")) {
                //用IOUtils进行两个流的快速拷贝
                IOUtils.copy(filePart.getInputStream(), stream);

                boolean flag = adminService.addMenu(m_name, m_price, m_desc);
                if (flag) {
                    out.write("添加成功！");
                } else {
                    out.write("添加失败!");
                }
            }
        }

//      boolean flag = adminService.addMenu(m_name, m_price, m_desc);
//
//
//        if (flag) {
//            out.write("{\"status\":\"success\",\"message\":\"添加成功！\"}");
//        } else {
//            out.write("{\"status\":\"fail\",\"error\":\"添加失败！\"}");
//        }

    }
}
