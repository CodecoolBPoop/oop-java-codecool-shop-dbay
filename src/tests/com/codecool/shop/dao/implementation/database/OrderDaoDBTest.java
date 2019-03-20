package com.codecool.shop.dao.implementation.database;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderDaoDBTest {
    @Test
    public void testIsGettingOrderReturnsExistingOrder(){
        OrderDao orderDao = OrderDaoDB.getInstance();
        Order order = orderDao.getOrder("0");
        System.out.println(order);
    }
}