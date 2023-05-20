package com.restaurantorder.ordertest.service.impl;

import com.restaurantorder.ordertest.dao.OrderMapper;
import com.restaurantorder.ordertest.entity.Order;
import com.restaurantorder.ordertest.service.OrderService;
import com.restaurantorder.ordertest.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    @Override
    public List<Order> getOrderListByUserName(String username) {
        try (SqlSession sqlSession = MybatisUtil.getSession()) {
            OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);

            return orderMapper.getOrderListByUserName(username);
        }
    }

    @Override
    public List<Order> searchOrderList(String orderName, String username) {
        try (SqlSession sqlSession = MybatisUtil.getSession()) {
            OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);

            return orderMapper.searchOrderListByUserName(orderName, username);
        }
    }

    @Override
    public List<Order> getOrderList() {
        try (SqlSession sqlSession = MybatisUtil.getSession()) {
            OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);

            return orderMapper.getOrderList();
        }
    }
}
