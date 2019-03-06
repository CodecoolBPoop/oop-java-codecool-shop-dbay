package com.codecool.shop.dao.implementation.memory;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Order;

import java.util.HashMap;

public class OrderDaoMem implements OrderDao {
    private HashMap<String, Order> orders = new HashMap<>();
    private static OrderDaoMem instance=null;

    private OrderDaoMem() {
    }

    public static OrderDaoMem getInstance(){
        if (instance==null){
            instance=new OrderDaoMem();
        }
        return instance;
    }


    @Override
    public Order getOrder(String sessionId) {
        return orders.get(sessionId);
    }

    @Override
    public void deleteOrder(String sessionId) {
        orders.remove(sessionId);
    }

    @Override
    public void addOrder(String sessionId, Order order) {
        orders.put(sessionId, order);
    }

    @Override
    public void updateOrder(String sessionId, Order order) {
        deleteOrder(sessionId);
        addOrder(sessionId, order);
    }
}
