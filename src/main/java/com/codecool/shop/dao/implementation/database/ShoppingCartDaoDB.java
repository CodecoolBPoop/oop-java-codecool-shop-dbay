package com.codecool.shop.dao.implementation.database;

import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ShoppingCart;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartDaoDB extends DaoDatabase implements ShoppingCartDao {

    private class LineItemDaoDB extends DaoDatabase {

        private List<LineItem> find(int cartId) {
            List<Object> values = new ArrayList<>();
            values.add(cartId);
            List<Object> objects = executeQuery("SELECT * FROM line_items WHERE \"cartId\"=?;", values);
            List<LineItem> lineItems = new ArrayList<>();
            for (Object object: objects) {
                if(object instanceof LineItem){
                    LineItem lineItem = (LineItem) object;
                    lineItem.setProduct(ProductDaoDB.getInstance().find(lineItem.getProductId()));
                    lineItems.add(lineItem);
                }
            }
            return lineItems;
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
        values.add(sessionId);
        values.add(0.0f);
        executeQuery("INSERT INTO shopping_carts (sessionId, totalPrice) VALUES (?, ?)", values);
        return new ShoppingCart(sessionId);
    }

    public void deleteShoppingCart(String sessionId) {
        List<Object> values1 = new ArrayList<>();
        values1.add(getShoppingCart(sessionId).getId());
        executeQuery("DELETE FROM line_items WHERE cartId=?", values1);
        List<Object> values2 = new ArrayList<>();
        values2.add(sessionId);
        executeQuery("DELETE FROM Shopping_carts WHERE sessionId=?;", values2);
    }

    public ShoppingCart getShoppingCart(String sessionId) {
        List<Object> values = new ArrayList<>();
        values.add(sessionId);
        ShoppingCart cart;
        try{
            cart = (ShoppingCart) executeQuery("SELECT * FROM shopping_carts WHERE \"sessionId\"=?;", values).get(0);
            LineItemDaoDB lineItemDaoDB = new LineItemDaoDB();
            List<LineItem> lineItems = lineItemDaoDB.find(cart.getId());
            cart.setLineItems(lineItems);
        } catch (Exception e) {
            cart = null;
        }

        return cart;

    }

    public void addToShoppingCart(ShoppingCart shoppingCart, Product product) {
        addToShoppingCart(shoppingCart.getSessionId(), product);
    }

    public void addToShoppingCart(String sessionId, Product product) {
        ShoppingCart shoppingCart = getShoppingCart(sessionId);
        if (shoppingCart != null) {
            LineItemDaoDB lineItemDaoDB = new LineItemDaoDB();
            LineItem matchingLineItem=null;
            for (LineItem lineItem: shoppingCart.getLineItems()) {
                if(lineItem.getProductId()==product.getId()){
                    matchingLineItem = lineItem;
                    break;
                }
            }
            if(matchingLineItem!=null){
                shoppingCart.addToShoppingCart(product);
                List<Object> values=new ArrayList<>();
                values.add(matchingLineItem.getQuantity()+1);
                values.add(matchingLineItem.getId());
                executeQuery("UPDATE line_items SET quantity=? WHERE id=?;", values);
            } else {
                shoppingCart.addToShoppingCart(product);

                List<Object> values = new ArrayList<>();
                values.add(product.getId());
                values.add(1);
                values.add(shoppingCart.getId());
                executeQuery("INSERT INTO line_items (productId, quantity, cartId) VALUES (?, ?, ?);", values);
            }
        } else {
            shoppingCart = createShoppingCart(sessionId);
            addToShoppingCart(shoppingCart.getSessionId(), product);
        }
    }

    public void removeFromShoppingCart(ShoppingCart shoppingCart, Product product) {
        removeFromShoppingCart(shoppingCart.getSessionId(), product);
    }

    public void removeFromShoppingCart(String sessionId, Product product) {
        ShoppingCart shoppingCart = getShoppingCart(sessionId);
        LineItemDaoDB lineItemDaoDB = new LineItemDaoDB();
        if(shoppingCart!=null){
            LineItem matchingLineItem = null;
            List<LineItem> lineItems = lineItemDaoDB.find(shoppingCart.getId());
            if(lineItems!=null){
                for (LineItem lineItem: lineItems) {
                    if(lineItem.getProductId() == product.getId()){
                        matchingLineItem = lineItem;
                    }
                }
                if(matchingLineItem!=null){
                    if(matchingLineItem.getQuantity()==1){
                        List<Object> values = new ArrayList<>();
                        values.add(shoppingCart.getId());
                        values.add(product.getId());
                        executeQuery("DELETE FROM line_items WHERE cartId=? AND productId=?", values);
                    } else {
                        List<Object> values = new ArrayList<>();
                        values.add(matchingLineItem.getQuantity()-1);
                        values.add(matchingLineItem.getId());
                        executeQuery("UPDATE line_items SET quantity=? WHERE id=?", values);
                    }
                    shoppingCart.removeFromShoppingCart(product);
                }
            }
        }
    }
}
