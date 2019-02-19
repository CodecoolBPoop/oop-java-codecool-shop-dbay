package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        // Add suppliers to data store.
        ArrayList<Supplier> suppliers = getSuppliers();
        for (Supplier supplier : suppliers) {
            supplierDataStore.add(supplier);
        }
        // Add Product Categories to data store.
        ArrayList<ProductCategory> vehicleProductCategories = getProductCategories();
        for (ProductCategory vehicleCategory: vehicleProductCategories) {
            productCategoryDataStore.add(vehicleCategory);
        }
        // Setting up products and printing them.
        String[] categories = {"Crossovers", "Hatchbacks", "Sedans", "Convertibles", "Vans", "Trucks", "Minivans", "Sport cars"};
        for (String style : categories) {
            for (ProductCategory category: vehicleProductCategories) {
                if (category.getName().equals(style)) {
                    Map<String, String[]> vehicles = getVehicles(style);
                    for (Map.Entry<String, String[]> element : vehicles.entrySet())
                    {
//                        System.out.println(element.getKey());
                        for (Supplier supplier: suppliers) {
                            if (supplier.getName().equals(element.getKey())) {
                                for (String car: element.getValue()) {
                                    String carName = element.getKey() + " " + car;
//                                    System.out.println(carName);
                                    productDataStore.add(new Product(carName, 49.9f, "USD", "Placeholder desc", category, supplier));
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private Map<String, String[]> getVehicles(String style) {
        Map<String, String[]> vehicles = new HashMap<String, String[]>();
        switch (style) {
            case "Crossovers":
                vehicles.put("Mercedes Benz", new String[] {"G-Class", "ML350", "GLE"});
                vehicles.put("Cadillac", new String[] {"Escalade", "XT5"});
                vehicles.put("Chevrolet", new String[] {"Traverse", "Tahoe"});
                vehicles.put("Rolls-Royce", new String[] {"Cullinan"});
                vehicles.put("Jaguar", new String[] {"F-Pace"});
                vehicles.put("Jeep", new String[] {"Grand Cherokee"});
                vehicles.put("Lamborghini", new String[] {"Urus"});
                vehicles.put("Hummer", new String[] {"H2"});
                vehicles.put("Lincoln", new String[] {"Navigator"});
                break;
            case "Hatchbacks":
                break;
            case "Sedans":
                vehicles.put("Aston Martin", new String[] {"DB11", "1958"});
                vehicles.put("Jaguar", new String[] {"FX", "XJ"});
                vehicles.put("Lamborghini", new String[] {"Estoque", "Huracan"});
                vehicles.put("Lexus", new String[] {"LS 500 Sport", "LS 500h"});
                vehicles.put("Mercedes Benz", new String[] {"A class", "240"});
                vehicles.put("Rolls-Royce", new String[] {"Phantom", "1925 Silver Ghost"});
                vehicles.put("Maybach", new String[] {"s600"});
                break;
            case "Convertibles":
                break;
            case "Vans":
                break;
            case "Trucks":
                break;
            case "Minivans":
                break;
            case "Sport cars":
                break;

        }
        return vehicles;
    }

    private ArrayList<Supplier> getSuppliers() {
        ArrayList<Supplier> suppliers = new ArrayList<>();
        String[] supplierNames = {"Mercedes Benz", "Cadillac", "Chevrolet", "Rolls-Royce", "Jaguar", "Jeep", "Lamborghini",
                                  "Hummer", "Lincoln", "Aston Martin", "Lexus", "Maybach"};
        for (String name: supplierNames) {
            Supplier supplier = new Supplier(name, "Placeholder");
            suppliers.add(supplier);
        }
        return suppliers;
    }

    private ArrayList<ProductCategory> getProductCategories() {
        ArrayList<ProductCategory> productCategories = new ArrayList<>();
        String[] categories = {"Crossovers", "Hatchbacks", "Sedans", "Convertibles", "Vans", "Trucks", "Minivans", "Sport cars"};
        for (String categoryName: categories) {
            ProductCategory category = new ProductCategory(categoryName, "Placeholder", "Placeholder desc");
            productCategories.add(category);
        }
        return productCategories;
    }
}
