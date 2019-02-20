package com.codecool.shop.model;

import java.util.ArrayList;

public class ShoppingCart {

    private ArrayList<LineItem> shoppingCart = new ArrayList<>();
    private String sessionId;

    public ShoppingCart() {
    }

    public ShoppingCart(String sessionId) {
        this.sessionId = sessionId;
    }

    public ArrayList<LineItem> getShoppingCart() {
        return shoppingCart;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public void addToShoppingCart(Product product) {
        boolean alreadyExists = false;
        for (LineItem lineItem: shoppingCart) {
            if (lineItem.getProduct().equals(product)){
                alreadyExists = true;
                lineItem.setQuantity(lineItem.getQuantity()+1);
            }
        }
        if(alreadyExists!=true){
            shoppingCart.add(new LineItem(product));
        }
    }

    public void removeFromShoppingCart(Product product){
        for (LineItem lineItem: shoppingCart) {
            if (lineItem.getProduct().equals(product)) {
                if (lineItem.getQuantity() != 0) {
                    lineItem.setQuantity(lineItem.getQuantity() - 1);
                } else {
                    shoppingCart.remove(lineItem);
                }
            }
        }
    }



}
