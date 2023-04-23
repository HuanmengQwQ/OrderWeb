package com.restaurantorder.ordertest.service.impl;

import com.restaurantorder.ordertest.MD5.MD5;
import com.restaurantorder.ordertest.dao.UserMapper;
import com.restaurantorder.ordertest.entity.User;
import com.restaurantorder.ordertest.service.UserService;
import com.restaurantorder.ordertest.util.MybatisUtil;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;

import java.security.NoSuchAlgorithmException;

public class UserServiceImpl implements UserService {

    @Override
    public boolean auth(String username, String password, HttpSession session) {
        try(SqlSession sqlSession = MybatisUtil.getSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            String encrypt_password = MD5.md5(password);
            User u = userMapper.getUser(username,encrypt_password);

            if(u == null) return false;
            else {
                session.setAttribute("username",username);
                return true;
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }
}
