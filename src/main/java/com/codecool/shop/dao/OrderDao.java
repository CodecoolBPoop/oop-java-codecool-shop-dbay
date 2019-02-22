package com.codecool.shop.dao;

import com.codecool.shop.model.Order;

public interface OrderDao {
    Order getOrder(String sessionId);

    void deleteOrder(String sessionId);

    void addOrder(String sessionId, Order order);

    void updateOrder(String sessionId, Order order);
}
