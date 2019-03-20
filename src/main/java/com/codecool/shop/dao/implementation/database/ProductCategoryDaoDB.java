package com.codecool.shop.dao.implementation.database;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;

import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoDB extends DaoDatabase implements ProductCategoryDao {
    private static ProductCategoryDaoDB instance;

    private ProductCategoryDaoDB() {}

    public static ProductCategoryDaoDB getInstance() {
        if (instance == null) {
            instance = new ProductCategoryDaoDB();
        }
        return instance;
    }

    @Override
    public void add(ProductCategory category) {
        List<Object> values = new ArrayList<>();
        values.add(category.getName());
        values.add(category.getDescription());
        values.add(category.getDepartment());
        executeQuery("INSERT INTO product_categories (name, description, department) VALUES (?, ?, ?);", values);
    }

    @Override
    public ProductCategory find(int id) {
        List<Object> values = new ArrayList<>();
        values.add(id);
        try{
            return (ProductCategory) executeQuery("SELECT * FROM product_categories WHERE id=?;", values).get(0);
        } catch (IndexOutOfBoundsException|NullPointerException e){
            return null;
        }
    }

    @Override
    public void remove(int id) {
        List<Object> values = new ArrayList<>();
        values.add(id);
        executeQuery("DELETE FROM product_categories WHERE id=?;", values);
    }

    @Override
    public List<ProductCategory> getAll() {
        List<Object> values = executeQuery("SELECT * FROM product_categories;", null);
        List<ProductCategory> productCategories = new ArrayList<>();
        for (Object value: values) {
            if(value instanceof ProductCategory) productCategories.add((ProductCategory)value);
        }
        return productCategories;
    }
}
