package com.codecool.shop.model;

import java.util.Currency;

public class Product extends BaseModel {

    private float defaultPrice;
    private Currency defaultCurrency;
    private ProductCategory productCategory;
    private Supplier supplier;


    public Product(String name, int topSpeed, double acceleration, int bhp, int modelYear, float defaultPrice, String currencyString, String description, ProductCategory productCategory, Supplier supplier) {
        super(name, description, acceleration, topSpeed,  bhp, modelYear);
        this.setPrice(defaultPrice, currencyString);
        this.setSupplier(supplier);
        this.setProductCategory(productCategory);
    }

    @Override
    public String toString() {
        return String.format("id: %1$d, " +
                        "name: %2$s, " +
                        "defaultPrice: %3$f, " +
                        "defaultCurrency: %4$s, " +
                        "productCategory: %5$s, " +
                        "supplier: %6$s",
                this.id,
                this.name,
                this.defaultPrice,
                this.defaultCurrency.toString(),
                this.productCategory.getName(),
                this.supplier.getName());
    }


    // GETTERS
    public float getDefaultPrice() { return defaultPrice; }
    public String getPrice() {
        String price = Float.toString(this.defaultPrice);
        price = price.substring(0, price.length() - 2);
        if (price.length() == 5) {
            price = price.substring(0, 2) + " " + price.substring(2);
        } else if (price.length() == 6) {
            price = price.substring(0, 3) + " " + price.substring(3);
        } else if (price.length() == 7) {
            price = price.substring(0, 1) + " " + price.substring(1, 4) + " " + price.substring(4);
        }
        return price + " " + this.defaultCurrency.toString();
    }
    public ProductCategory getProductCategory() { return productCategory; }
    public Currency getDefaultCurrency() { return defaultCurrency; }
    public Supplier getSupplier() { return supplier; }
    // SETTERS
    public void setDefaultPrice(float defaultPrice) { this.defaultPrice = defaultPrice; }
    public void setDefaultCurrency(Currency defaultCurrency) { this.defaultCurrency = defaultCurrency; }
    public void setPrice(float price, String currency) {
        this.defaultPrice = price;
        this.defaultCurrency = Currency.getInstance(currency);
    }
    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
        this.productCategory.addProduct(this);
    }
    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
        this.supplier.addProduct(this);
    }
}
