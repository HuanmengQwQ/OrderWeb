package com.restaurantorder.ordertest.service.impl;

import com.restaurantorder.ordertest.MD5.MD5;
import com.restaurantorder.ordertest.dao.AdminMapper;
import com.restaurantorder.ordertest.entity.Admin;
import com.restaurantorder.ordertest.entity.Food;
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
                session.setAttribute("admin", a);
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean ChangeAnnouncements(String note) {
        try (SqlSession sqlSession = MybatisUtil.getSession()) {
            AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);
            boolean flag = adminMapper.changeNote(note);
            if (flag) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean addMenu(String m_name, double price, String desc) {
        SqlSession sqlSession;
        sqlSession = MybatisUtil.getCloseSession();
        try {
            AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);
            Food f = new Food(m_name, price, desc);
            adminMapper.addMenu(f);

            sqlSession.commit();
            return true;
        } catch (Exception e) {
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }

        return false;
    }


}
