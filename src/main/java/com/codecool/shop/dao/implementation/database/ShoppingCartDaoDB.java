package com.codecool.shop.dao.implementation.database;

import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ShoppingCart;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartDaoDB extends DaoDatabase implements ShoppingCartDao {

    private class LineItemDao {

        private LineItem find(String sessionId) {
            List<Object> values = new ArrayList<>();
            values.add(sessionId);
            return (LineItem) executeQuery("SELECT * FROM line_items WHERE session_id=?;", values).get(0);
        }

    }

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
        List<Object> values = new ArrayList<>();
        if (sessionId.equals(getShoppingCart(sessionId))) {
            values.add(sessionId);
        }
        return (ShoppingCart) values;
    }

    public void deleteShoppingCart(String sessionId) {
        List<Object> values = new ArrayList<>();
        values.add(sessionId);
        executeQuery("DELETE FROM Shopping_carts WHERE sessionid=?;", values);
    }

    public ShoppingCart getShoppingCart(String sessionId) {
        List<Object> values = new ArrayList<>();
        values.add(sessionId);
        ShoppingCart shoppingCart = (ShoppingCart) executeQuery(
                "SELECT * FROM shopping_carts JOIN line_items " +
                        "ON shopping_carts.id=line_items.cartId " +
                        "WHERE shopping_cart.session_id=?;", values);

        return shoppingCart;

    }

    public void addToShoppingCart(ShoppingCart shoppingCart, Product product) {
        List<Object> values = new ArrayList<>();
        values.add(shoppingCart.getSessionId());
        values.add(shoppingCart.getTotalPrice());
        executeQuery("INSERT INTO shopping_cart (sessionid, totalprice) VALUES (?, ?);", values);
    }

    public void addToShoppingCart(String sessionId, Product product) {
        List<Object> values1 = new ArrayList<>();
        List<Object> values2 = new ArrayList<>();
        List<Object> values3 = new ArrayList<>();
        List<Object> values4 = new ArrayList<>();
        ShoppingCart shoppingCart = null;
        values1.add(sessionId);
        try {
            shoppingCart = (ShoppingCart) executeQuery("SELECT * FROM shopping_carts WHERE sessionid=?;", values1).get(0);
        } catch (Exception ex) {
        }
        if (shoppingCart != null) {
            int productQuantity;
            values2.add(shoppingCart.getId());
            values2.add(product.getId());
            executeQuery("SELECT * FROM line_items WHERE cartId=? AND productId=?;", values2 );
            if (shoppingCart.getLineItems().size() != 0) {
                for (LineItem item : shoppingCart.getLineItems()) {
                    if (item.getProductId() == product.getId()) {
                        productQuantity = item.getQuantity();
                        values3.add(productQuantity + 1);
                        executeQuery("UPDATE line_items SET quantity=?", values3);
                        break;
                    }
                }
            } else {
                shoppingCart.addToShoppingCart(product);
                values4.add(product.getId());
                values4.add(shoppingCart.getId());
                values4.add(sessionId);
                executeQuery("INSERT INTO line_items (productId, quantity, cartId, sessionId) VALUES (?, 1, ?, ?)", values4);
            }
        }
        else {
            shoppingCart = new ShoppingCart();
        }
    }

    public void removeFromShoppingCart(ShoppingCart shoppingCart, Product product) {
        List<Object> values = new ArrayList<>();
        values.add(shoppingCart.getSessionId());
        values.add(shoppingCart.getLineItems());
        executeQuery("DELETE FROM shopping_carts WHERE sessionid=?;", values);
    }

    public void removeFromShoppingCart(String sessionId, Product product) {
//        List<Object> values = new ArrayList<>();
//        values.add(sessionId);
//        executeQuery("DELETE FROM Shopping_carts WHERE sessionid=?;", values);
    }
}
