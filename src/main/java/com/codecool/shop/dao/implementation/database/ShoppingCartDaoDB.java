package com.codecool.shop.dao.implementation.database;

import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ShoppingCart;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartDaoDB extends DaoDatabase implements ShoppingCartDao {

    private static ShoppingCartDaoDB instance;

    private ShoppingCartDaoDB() {
    }

    public static ShoppingCartDaoDB getInstance() {
        if (instance == null) {
            instance = new ShoppingCartDaoDB();
        }
        return instance;
    }

    public ShoppingCart createShoppingCart(String sessionId) {

        return null;
    }

    public void deleteShoppingCart(String sessionId) {
        List<Object> values = new ArrayList<>();
        values.add(sessionId);
        executeQuery("DELETE FROM Shopping_carts WHERE sessionid=?;", values);
    }

    public ShoppingCart getShoppingCart(String sessionId) {
        List<Object> values = executeQuery("SELECT * FROM shopping_cart WHERE sessionid=? ;", null);
        return (ShoppingCart) values;

    }

    public void addToShoppingCart(ShoppingCart shoppingCart, Product product) {
        List<Object> values = new ArrayList<>();
        values.add(shoppingCart.getSessionId());
        values.add(shoppingCart.getTotalPrice());
        executeQuery("INSERT INTO shopping_cart (sessionid, totalprice) VALUES (?, ?);", values);
    }

//    public void addToShoppingCart(String sessionId, Product product) {
//        List<Object> values = new ArrayList<>();
//        values.add(sessionId);
//        values.add(product.getDefaultPrice());
//        executeQuery("INSERT INTO shopping_cart (sessionid, totalprice) VALUES (?, ?);", values);
//    }

    public void removeFromShoppingCart(ShoppingCart shoppingCart, Product product) {
        List<Object> values = new ArrayList<>();
        values.add(shoppingCart.getSessionId());
        values.add(shoppingCart.getLineItems());
        executeQuery("DELETE FROM shopping_carts WHERE sessionid=?;", values);
    }

//    public void removeFromShoppingCart(String sessionId, Product product) {
//        List<Object> values = new ArrayList<>();
//        values.add(sessionId);
//        executeQuery("DELETE FROM Shopping_carts WHERE sessionid=?;", values);
//    }
}
