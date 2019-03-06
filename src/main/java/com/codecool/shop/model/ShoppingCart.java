package com.codecool.shop.model;

import java.math.BigDecimal;
import java.util.ArrayList;

public class ShoppingCart {

    private int id;
    private ArrayList<LineItem> lineItems = new ArrayList<>();
    private String sessionId;
    private float totalPrice;


    public String getTotalPrice() {
        return round(this.totalPrice, 2) + " USD";
    }

    public ShoppingCart() {
        this.id = 0;
        this.totalPrice = 0.0f;
    }

    public ShoppingCart(String sessionId) {
        this.id = 0;
        this.sessionId = sessionId;
        this.totalPrice = 0.0f;
    }

    public ArrayList<LineItem> getLineItems() {
        return lineItems;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public void addToShoppingCart(Product product) {
        boolean alreadyExists = false;
        for (LineItem lineItem: lineItems) {
            if (lineItem.getProduct().equals(product)){
                alreadyExists = true;
                lineItem.setQuantity(lineItem.getQuantity()+1);
            }
        }
        if(alreadyExists!=true){
            lineItems.add(new LineItem(product));
        }
        totalPrice+=product.getDefaultPrice();
    }

    public void removeFromShoppingCart(Product product){
        for (LineItem lineItem: lineItems) {
            if (lineItem.getProduct().equals(product)) {
                if (lineItem.getQuantity() != 0) {
                    lineItem.setQuantity(lineItem.getQuantity() - 1);
                } else {
                    lineItems.remove(lineItem);
                }
            }
        }
        totalPrice-=product.getDefaultPrice();
    }

    private static float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }

}
