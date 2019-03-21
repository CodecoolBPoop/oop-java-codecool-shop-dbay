package com.codecool.shop.dao.implementation.database;

import com.codecool.shop.model.*;

public class WebshopEntityFactory {
    public static Object getInstanceOfWebshopEntity(Class entityDaoClass){

        switch (entityDaoClass.getName()){
            case "com.codecool.shop.dao.implementation.database.ProductCategoryDaoDB":
                return new ProductCategory();
            case "com.codecool.shop.dao.implementation.database.ProductDaoDB":
                return new Product();
            case "com.codecool.shop.dao.implementation.database.SupplierDaoDB":
                return new Supplier();
            case "com.codecool.shop.dao.implementation.database.OrderDaoDB":
                return new Order();
            case "com.codecool.shop.dao.implementation.database.OrderDaoDB$AddressDaoDB":
                return new Address();
            case "com.codecool.shop.dao.implementation.database.OrderDaoDB$PersonalInfoDaoDB":
                return new PersonalInfo();
            case "com.codecool.shop.dao.implementation.database.ShoppingCartDaoDB":
                return new ShoppingCart();
            case "com.codecool.shop.dao.implementation.database.ShoppingCartDaoDB$LineItemDaoDB":
                return new LineItem();
            case "com.codecool.shop.dao.implementation.database.LoginDaoDB":
                return new User();
            default:
                return null;
        }
    }
}
