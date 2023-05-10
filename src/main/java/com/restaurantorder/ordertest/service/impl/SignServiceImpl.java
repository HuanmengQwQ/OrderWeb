package com.restaurantorder.ordertest.service.impl;

import com.restaurantorder.ordertest.MD5.MD5;
import com.restaurantorder.ordertest.dao.UserMapper;
import com.restaurantorder.ordertest.entity.User;
import com.restaurantorder.ordertest.service.SignService;
import com.restaurantorder.ordertest.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

public class SignServiceImpl implements SignService {
    @Override
    public boolean check(String username, String password, String repeatpassword) {
        try (SqlSession sqlSession = MybatisUtil.getSession()) {
            String saltPassword = MD5.md5(password);

            if (password.equals(repeatpassword)) {

                UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
                User u = userMapper.getUserByName(username);
                //判断用户名是否存在
                if (u != null) {
                    User user = new User(username, saltPassword);
                    int i = userMapper.setUser(user);
                    if (i > 0) {
                        return true;
                    }
                }
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
