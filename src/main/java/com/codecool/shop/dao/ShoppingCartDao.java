package com.codecool.shop.dao;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ShoppingCart;

public interface ShoppingCartDao {

    void createShoppingCart(String sessionId);

    void deleteShoppingCart(String sessionId);

    ShoppingCart getShoppingCart(String sessionId);

    void addToShoppingCart(ShoppingCart shoppingCart, Product product);

    void addToShoppingCart(String sessionId, Product product);

    void removeFromShoppingCart(ShoppingCart shoppingCart, Product product);

    void removeFromShoppingCart(String sessionId, Product product);
}
