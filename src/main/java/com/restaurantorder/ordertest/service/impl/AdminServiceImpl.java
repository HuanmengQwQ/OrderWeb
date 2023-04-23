package com.restaurantorder.ordertest.service.impl;

import com.restaurantorder.ordertest.MD5.MD5;
import com.restaurantorder.ordertest.dao.AdminMapper;
import com.restaurantorder.ordertest.entity.Admin;
import com.restaurantorder.ordertest.entity.User;
import com.restaurantorder.ordertest.service.AdminService;
import com.restaurantorder.ordertest.util.MybatisUtil;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;

public class AdminServiceImpl implements AdminService {
    @Override
    public boolean auth(String name, String password, HttpSession session) {
        try(SqlSession sqlSession = MybatisUtil.getSession()) {
            AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);
            String encrypt_password = MD5.md5(password);
            Admin a = adminMapper.getAdmin(name,encrypt_password);

            if(a != null){
                session.setAttribute("admin",a);
                return true;
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }
}
