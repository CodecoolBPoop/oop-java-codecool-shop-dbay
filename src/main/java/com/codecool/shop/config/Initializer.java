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
        // Get descriptions.
        Map<String, double[]> descriptions = getDescriptions();
        // Setting up products and printing them.
        String[] categories = {"Crossovers", "Hatchbacks", "Sedans", "Convertibles", "Vans", "Trucks", "Minivans", "Sport cars"};
        for (String style : categories) {
            for (ProductCategory category: vehicleProductCategories) {
                if (category.getName().equals(style)) {
                    findVehicle(productDataStore, suppliers, style, category ,descriptions);
                }
            }
        }
    }

    private void findVehicle(ProductDao productDataStore, ArrayList<Supplier> suppliers, String style, ProductCategory category, Map<String, double[]> descriptions) {
        Map<String, String[]> vehicles = getVehicles(style);
        for (Map.Entry<String, String[]> element : vehicles.entrySet())
        {
            for (Supplier supplier: suppliers) {
                if (supplier.getName().equals(element.getKey())) {
                    findVehicleName(productDataStore, category, element, supplier, descriptions);
                }
            }
        }
    }

    private void findVehicleName(ProductDao productDataStore, ProductCategory category, Map.Entry<String, String[]> element, Supplier supplier, Map<String, double[]> descriptions) {
        double[] description = {0.0,0.0,0.0};
        for (String car: element.getValue()) {
            for (Map.Entry<String, double[]> desc : descriptions.entrySet()) {
                if (desc.getKey().equals(car)) {
                    description = desc.getValue();
                    break;
                }
            }
            String carName = element.getKey() + " " + car;
            productDataStore.add(new Product(carName, description[0],(int) description[1],(int) description[2], 49.9f, "USD", "Placeholder desc", category, supplier));
        }
    }


    private Map<String, String[]> getVehicles(String style) {
        Map<String, String[]> vehicles = new HashMap<>();
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
                vehicles.put("Aston Martin", new String[] {"DB11", "DB4", "DB9"});
                vehicles.put("Jaguar", new String[] {"XF", "XJ"});
                vehicles.put("Lamborghini", new String[] {"Estoque", "Huracan"});
                vehicles.put("Lexus", new String[] {"LS 500 Sport", "LS 500h"});
                vehicles.put("Mercedes Benz", new String[] {"A class", "E300"});
                vehicles.put("Rolls-Royce", new String[] {"Phantom", "Silver Ghost", "Ghost"});
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

    private Map<String, double[]> getDescriptions() {
        Map<String, double[]> descriptions = new HashMap<>();
        descriptions.put("G-Class", new double[] {4.4, 536, 2017});
        descriptions.put("ML350", new double[] {6.8, 254, 2015});
        descriptions.put("GLE", new double[] {5.5, 384, 2019});
        descriptions.put("Escalade", new double[] {3.9, 403, 2019});
        descriptions.put("XT5", new double[] {6.6, 310, 2019});
        descriptions.put("Traverse", new double[] {6.7, 281, 2018});
        descriptions.put("Tahoe", new double[] {5.7, 420, 2019});
        descriptions.put("Cullinan", new double[] {6.7, 563, 2018});
        descriptions.put("F-Pace", new double[] {6.4, 247, 2018});
        descriptions.put("Grand Cherokee", new double[] {7.6, 188, 2017});
        descriptions.put("Urus", new double[] {3.5, 640, 2018});
        descriptions.put("H2", new double[] {7.6, 392, 2007});
        descriptions.put("Navigator", new double[] {4.8, 450, 2019});
        descriptions.put("DB11", new double[] {3.9, 630, 2016});
        descriptions.put("DB4", new double[] {9.0, 240, 1958});
        descriptions.put("DB9", new double[] {4.5, 450, 2004});
        descriptions.put("XF", new double[] {5.1, 374, 2015});
        descriptions.put("XJ", new double[] {4.2, 566, 2010});
        descriptions.put("Estoque", new double[] {2.9, 560, 2008});
        descriptions.put("Huracan", new double[] {2.3 , 631, 2018});
        descriptions.put("LS 500 Sport", new double[] {4.5 , 416, 2018});
        descriptions.put("LS 500h", new double[] {5.3 , 354, 2018});
        descriptions.put("A class", new double[] {4.5 , 301, 2019});
        descriptions.put("E300", new double[] {6.2 , 241, 2019});
        descriptions.put("Phantom", new double[] {5.5 , 453, 2019});
        descriptions.put("Silver Ghost", new double[] {17.0 , 80, 1925});
        descriptions.put("Ghost", new double[] {4.9 , 563, 2019});
        descriptions.put("s600", new double[] {3.7 , 493, 2017});
        return descriptions;
    }
}
