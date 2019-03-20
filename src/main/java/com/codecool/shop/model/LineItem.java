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
        this.productId=product.getId();
        this.quantity = 1;
        this.id=0;
    }

    public LineItem(Product product, int cartId) {
        this(product);
        this.cartId=cartId;
    }

    public LineItem(int quantity, Product product) {
        this(product);
        this.quantity=quantity;
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


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
