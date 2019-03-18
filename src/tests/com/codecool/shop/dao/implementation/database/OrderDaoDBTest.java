package com.codecool.shop.dao.implementation.database;

import com.codecool.shop.dao.OrderDao;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderDaoDBTest {
    @Test
    public void testIsGettingOrderReturnsExistingOrder(){
        OrderDao orderDao = OrderDaoDB.getInstance();
        orderDao.getOrder("0");
    }
}