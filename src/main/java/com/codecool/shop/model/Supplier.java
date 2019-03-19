package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.List;

public class Supplier extends BaseModel {
    private List<Product> products = new ArrayList<>();

    public Supplier(String name, String description) {
        super(name, description);
        this.products = new ArrayList<>();
    }

    public Supplier() {
    }

    public String toString() {
        return String.format("id: %1$d, " +
                        "name: %2$s, " +
                        "description: %3$s",
                this.id,
                this.name,
                this.description
        );
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    // GETTERS
    public List<Product> getProducts() {
        return this.products;
    }
    // SETTERS
    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }


}