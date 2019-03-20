package com.codecool.shop.dao.implementation.database;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.model.User;

public class WebshopEntityFactory {
    public static Object getInstanceOfWebshopEntity(Class entityClass){
        switch (entityClass.getName()){
            case "com.codecool.shop.dao.implementation.database.ProductCategoryDaoDB":
                return new ProductCategory();
            case "com.codecool.shop.dao.implementation.database.ProductDaoDB":
                return new Product();
            case "com.codecool.shop.dao.implementation.database.SupplierDaoDB":
                return new Supplier();
            case "com.codecool.shop.dao.implementation.database.LoginDaoDB":
                return new User();
            default:
                return null;
        }
    }
}
