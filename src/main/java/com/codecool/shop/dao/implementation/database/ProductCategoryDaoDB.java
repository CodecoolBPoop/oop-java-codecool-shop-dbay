package com.codecool.shop.dao.implementation.database;

import com.codecool.shop.dao.ProductCategoryDao;
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
        executeQuery("INSERT INTO Suppliers (name, description) VALUES (?, ?);", values);
    }

    @Override
    public ProductCategory find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<ProductCategory> getAll() {
        return null;
    }
}
