package com.codecool.shop.dao.implementation.database;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ShoppingCart;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartDaoDBTest {
    @Test
    public void testFindingExistingShoppingCartWorks(){
        ShoppingCartDao shoppingCartDao = ShoppingCartDaoDB.getInstance();
        ShoppingCart shoppingCart = shoppingCartDao.getShoppingCart("johnny");
        System.out.println(shoppingCart);
    }

    @Test
    public void testFindingNotExistingShoppingCartReturnsNull(){
        assertNull(ShoppingCartDaoDB.getInstance().getShoppingCart("egfdg"));
    }

    @Test
    public void testAddingProductToShoppingCartCreatesNewCartOrLineItemOrIncreasesQuantity(){
        ShoppingCartDao shoppingCartDao = ShoppingCartDaoDB.getInstance();
        ProductDao productDao = ProductDaoDB.getInstance();
        Product product = productDao.find(20);
        shoppingCartDao.addToShoppingCart("johnny", product);
        System.out.println(product);
    }

    @Test
    public void testRemovingFromShoppingCartDecreasesQuantityOrDeletesLineItem(){
        ShoppingCartDao shoppingCartDao = ShoppingCartDaoDB.getInstance();
        ProductDao productDao = ProductDaoDB.getInstance();
        Product product = productDao.find(20);
        shoppingCartDao.removeFromShoppingCart("johnny", product);
        System.out.println(product);
    }

    @Test
    public void testDeletingShoppingCartRemovesCartAndAllLineItemsFromDatabase(){
        ShoppingCartDao shoppingCartDao = ShoppingCartDaoDB.getInstance();
        shoppingCartDao.deleteShoppingCart("johnny");
    }
}