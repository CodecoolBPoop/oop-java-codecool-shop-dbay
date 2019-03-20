package com.codecool.shop.dao.implementation.database;

import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.model.ShoppingCart;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartDaoDBTest {
    @Test
    public void testFindingExistingShoppingCartWorks(){
        ShoppingCartDao shoppingCartDao = ShoppingCartDaoDB.getInstance();
        ShoppingCart shoppingCart = shoppingCartDao.getShoppingCart("bré");
        System.out.println(shoppingCart);
    }
}