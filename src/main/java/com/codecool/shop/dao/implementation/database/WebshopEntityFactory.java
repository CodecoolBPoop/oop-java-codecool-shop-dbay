package com.codecool.shop.dao.implementation.database;

import com.codecool.shop.model.ProductCategory;

public class WebshopEntityFactory {
    public static Object getInstanceOfWebshopEntity(Class entityClass){
        switch (entityClass.getName()){
            case "com.codecool.shop.dao.implementation.database.ProductCategoryDaoDB":
                return new ProductCategory();
        }
    }
}
