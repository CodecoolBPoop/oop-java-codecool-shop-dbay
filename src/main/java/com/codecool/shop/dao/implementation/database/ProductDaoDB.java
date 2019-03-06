package com.codecool.shop.dao.implementation.database;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.util.ArrayList;
import java.util.List;

public class ProductDaoDB extends DaoDatabase implements ProductDao {

    private static ProductDaoDB instance;

    private ProductDaoDB() {}

    public static ProductDaoDB getInstance() {
        if (instance == null) {
            instance = new ProductDaoDB();
        }
        return instance;
    }

    @Override
    public void add(Product product) {
        List<Object> values = new ArrayList<>();
        values.add(product.getName());
        values.add(product.getDescription());
        values.add(product.getDefaultPrice());
        values.add(product.getDefaultCurrency());
        values.add(product.getProductCategory());
        values.add(product.getSupplier());
        values.add(product.getBhp());
        values.add(product.getAcceleration());
        values.add(product.getModelYear());
        values.add(product.getTopSpeed());
        executeQuery("INSERT INTO Products (name, description, defaultprice, defaultcurrency, productcategory, supplier, bhp, acceleration, modelyear, topspeed) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);", values);
    }

    @Override
    public Product find(int id) {
        List<Object> values = new ArrayList<>();
        values.add(id);
        return (Product) executeQuery("SELECT * FROM Products WHERE id=?;", values).get(0);
    }

    @Override
    public void remove(int id) {
        List<Object> values = new ArrayList<>();
        values.add(id);
        executeQuery("DELETE FROM Products WHERE id=?;", values);
    }

    @Override
    public List<Product> getAll() {
        List<Object> values = executeQuery("SELECT * FROM Products;", null);
        return getProducts(values);
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        List<Object> values = new ArrayList<>();
        values.add(supplier);
        List<Object> suppliers = executeQuery("SELECT * FROM Products WHERE supplier=?;", values);
        return getProducts(suppliers);
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        List<Object> values = new ArrayList<>();
        values.add(productCategory);
        List<Object> productcategory = executeQuery("SELECT * FROM Products WHERE productcategory=?;", values);
        return getProducts(productcategory);
    }

    private List<Product> getProducts(List<Object> productcategory) {
        List<Product> productList = new ArrayList<>();
        for (Object value : productcategory) {
            if (value instanceof Product) productList.add((Product) value);
        }
        return productList;
    }
}