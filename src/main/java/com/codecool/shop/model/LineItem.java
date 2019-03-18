package com.codecool.shop.model;

import java.text.DecimalFormat;

public class LineItem{
    private int id;
    private int quantity;
    private Product product;
    private int productId;
    private int cartId;

    public LineItem() {
        this.id=0;
    }

    public LineItem(Product product) {
        this.product = product;
        this.quantity = 1;
        this.id=0;
    }

    public LineItem(int quantity, Product product) {
        this.quantity = quantity;
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

}
