package com.codecool.shop.model;

public class LineItem{
    private int quantity;
    private Product product;

    public LineItem() {
    }

    public LineItem(Product product) {
        this.product = product;
        this.quantity = 1;
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


}
