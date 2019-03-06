package com.codecool.shop.dao.implementation.memory;

import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ShoppingCart;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCartDaoMem implements ShoppingCartDao {

    private Map<String, ShoppingCart> shoppingCarts = new HashMap<>();
    private static ShoppingCartDaoMem instance = null;

    private ShoppingCartDaoMem() {
    }

    public static ShoppingCartDaoMem getInstance() {
        if (instance == null) {
            instance = new ShoppingCartDaoMem();
        }
        return instance;
    }

    public ShoppingCart createShoppingCart(String sessionId) {
        ShoppingCart cart = new ShoppingCart(sessionId);
        shoppingCarts.put(sessionId, cart);
        return cart;
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
        if (shoppingCarts.get(sessionId)!= null) {
            shoppingCarts.get(sessionId).addToShoppingCart(product);
        } else {
            ShoppingCart cart = createShoppingCart(sessionId);
            cart.addToShoppingCart(product);
        }
    }

    public void removeFromShoppingCart(ShoppingCart shoppingCart, Product product) {
        shoppingCart.removeFromShoppingCart(product);
    }

    public void removeFromShoppingCart(String sessionId , Product product) {
        shoppingCarts.get(sessionId).removeFromShoppingCart(product);
    }
}
