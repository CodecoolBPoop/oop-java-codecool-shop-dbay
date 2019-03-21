package com.codecool.shop.dao.implementation.database;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Address;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.PersonalInfo;
import com.codecool.shop.model.ShoppingCart;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderDaoDBTest {
    @Test
    public void testIsGettingOrderReturnsExistingOrder(){
        OrderDao orderDao = OrderDaoDB.getInstance();
        Order order = orderDao.getOrder("norbó");
        System.out.println(order);
    }

    @Test
    public void testIsPlacingAnOrderPutsAllTheDataInPlace(){
        ShoppingCartDaoDB.getInstance().addToShoppingCart("norbó", ProductDaoDB.getInstance().find(11));
        ShoppingCart shoppingCart = ShoppingCartDaoDB.getInstance().getShoppingCart("norbó");
        PersonalInfo personalInfo = new PersonalInfo("Norbert", "Pap", "garrote713@gmail.com", "+36303791667");
        Address billingAddress = new Address("Switzerland", "Geneva", 2030, "Maria strasse 13.");
        Address shippingAddress = new Address("Portugal", "Lisbon", 1111, "tbevfbt");
        OrderDao orderDao = OrderDaoDB.getInstance();
        Order order = new Order("norbó", personalInfo, shoppingCart, billingAddress, shippingAddress);
        orderDao.addOrder("norbó", order);
    }
}