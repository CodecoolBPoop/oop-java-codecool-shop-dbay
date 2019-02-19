package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ShoppingCart;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCartDaoMem implements ShoppingCartDao {

    private Map<String, ShoppingCart> shoppingCarts = new HashMap<>();


    public void createShoppingCart(String sessionId) {
        shoppingCarts.put(sessionId, new ShoppingCart(sessionId));
    }

    public void deleteShoppingCart(String sessionId) {
        shoppingCarts.remove(sessionId);
    }

    public ShoppingCart getShoppingCart(String sessionId) {
        return shoppingCarts.get(sessionId);
    }

    public void addToShoppingCart(ShoppingCart shoppingCart, Product product) {
        shoppingCart.addToShoppingCart(product);
    }

    public void addToShoppingCart(String sessionId, Product product) {
        shoppingCarts.get(sessionId).addToShoppingCart(product);
    }

    public void removeFromShoppingCart(ShoppingCart shoppingCart, Product product) {
        shoppingCart.removeFromShoppingCart(product);
    }

    public void removeFromShoppingCart(String sessionId , Product product) {
        shoppingCarts.get(sessionId).removeFromShoppingCart(product);
    }
}
