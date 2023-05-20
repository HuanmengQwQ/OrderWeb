package com.restaurantorder.ordertest.service.impl;

import com.restaurantorder.ordertest.dao.CartMapper;
import com.restaurantorder.ordertest.entity.Cart;
import com.restaurantorder.ordertest.service.CartService;
import com.restaurantorder.ordertest.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class CarServiceImpl implements CartService {

    //获取购物车列表
    @Override
    public List<Cart> getCartList(String name) {
        try (SqlSession sqlSession = MybatisUtil.getSession()) {
            CartMapper cartMapper = sqlSession.getMapper(CartMapper.class);

            return cartMapper.getCartList(name);
        }
    }

    @Override
    public Cart getCartFoodByMid(int mid) {
        try (SqlSession sqlSession = MybatisUtil.getSession()) {
            CartMapper cartMapper = sqlSession.getMapper(CartMapper.class);

            return cartMapper.getCartFoodByMid(mid);
        }
    }

    @Override
    public void addCartFood(Cart cart) {
        SqlSession sqlSession;
        sqlSession = MybatisUtil.getCloseSession();
        try {
            CartMapper cartMapper = sqlSession.getMapper(CartMapper.class);
            cartMapper.addCart(cart);

            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void changeCartFood(Cart cart) {
        SqlSession sqlSession;
        sqlSession = MybatisUtil.getCloseSession();

        try {
            CartMapper cartMapper = sqlSession.getMapper(CartMapper.class);
            cartMapper.changeCartFood(cart);

            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public boolean delCartFood(String username, int mid) {
        SqlSession sqlSession;
        sqlSession = MybatisUtil.getCloseSession();
        boolean flag = false;
        try {
            CartMapper cartMapper = sqlSession.getMapper(CartMapper.class);
            flag = cartMapper.delCartFood(username, mid);

            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return flag;
    }

    @Override
    public boolean clearCart(String username) {
        SqlSession sqlSession;
        sqlSession = MybatisUtil.getCloseSession();
        boolean flag = false;

        try {
            CartMapper cartMapper = sqlSession.getMapper(CartMapper.class);
            flag = cartMapper.clearCart(username);

            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return flag;
    }

    @Override
    public boolean checkoutByname(String username) {
        SqlSession sqlSession;
        sqlSession = MybatisUtil.getCloseSession();
        boolean flag = false;

        try {
            CartMapper cartMapper = sqlSession.getMapper(CartMapper.class);
            flag = cartMapper.checkout(username);

            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }

        return flag;
    }
}
