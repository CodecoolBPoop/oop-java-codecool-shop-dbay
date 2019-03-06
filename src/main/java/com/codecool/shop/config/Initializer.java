package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.memory.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.memory.ProductDaoMem;
import com.codecool.shop.dao.implementation.memory.SupplierDaoMem;
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
                    break;
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
                    break;
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
            productDataStore.add(new Product(carName, (int) description[0], description[1],(int) description[2],(int) description[3], (float) description[4], "USD", "Placeholder desc", category, supplier));
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
                vehicles.put("Honda", new String[] {"Civic Type R"});
                vehicles.put("Volkswagen", new String[] {"Golf GTI", "Scirocco R", "Polo"});
                vehicles.put("Ferrari", new String[] {"FF"});
                vehicles.put("Ford", new String[] {"Focus RS", "Fiesta"});
                vehicles.put("Toyota", new String[] {"Corolla XSE"});
                vehicles.put("Mazda", new String[] {"3"});
                vehicles.put("Chevrolet", new String[] {"Sonic RS", "Cruze"});
                vehicles.put("Hyundai", new String[] {"Veloster"});
                break;
            case "Sedans":
                vehicles.put("Aston Martin", new String[] {"DB11", "DB4", "DB9"});
                vehicles.put("Jaguar", new String[] {"XF", "XJ"});
                vehicles.put("Lamborghini", new String[] {"Estoque"});
                vehicles.put("Dodge", new String[] {"Charger SRT"});
                vehicles.put("Lexus", new String[] {"LS 500 Sport", "LS 500h"});
                vehicles.put("Mercedes Benz", new String[] {"A-Class", "E300"});
                vehicles.put("Rolls-Royce", new String[] {"Phantom", "Silver Ghost", "Ghost"});
                vehicles.put("Maybach", new String[] {"s600"});
                break;
            case "Convertibles":
                vehicles.put("Spyker", new String[] {"C8"});
                vehicles.put("Honda", new String[] {"S2000"});
                vehicles.put("Mazda", new String[] {"MX5"});
                vehicles.put("BMW", new String[] {"M4", "M6"});
                vehicles.put("Mercedes Benz", new String[] {"CL180", "C300", "S63", "S550"});
                break;
            case "Vans":
                vehicles.put("Citroen", new String[] {"Berlingo"});
                vehicles.put("Mercedes Benz", new String[] {"V250", "Sprinter"});
                vehicles.put("Peugeot", new String[] {"Boxer", "Partner"});
                break;
            case "Trucks":
                vehicles.put("Ford", new String[] {"Raptor", "Ranger"});
                vehicles.put("Chevrolet", new String[] {"Silverado"});
                vehicles.put("Dodge", new String[] {"Ram"});
                vehicles.put("Toyota", new String[] {"Hilux", "Tacoma"});
                vehicles.put("Nissan", new String[] {"Frontier", "Titan"});
                break;
            case "Minivans":
                vehicles.put("Chrysler", new String[] {"Pacifica", "Town & Country"});
                vehicles.put("Kia", new String[] {"Sedona"});
                vehicles.put("Dodge", new String[] {"Grand Caravan"});
                vehicles.put("Toyota", new String[] {"Sienna"});
                break;
            case "Sport cars":
                vehicles.put("Ferrari", new String[] {"Laferrari", "812 Superfast", "488GTB"});
                vehicles.put("Bugatti", new String[] {"Chiron"});
                vehicles.put("Pagani", new String[] {"Huayra"});
                vehicles.put("Porsche", new String[] {"911 GT3", "918"});
                vehicles.put("Ford", new String[] {"GT"});
                vehicles.put("Lamborghini", new String[] {"Huracan"});
                vehicles.put("Koenigsegg", new String[] {"Regera"});
                vehicles.put("Zenvo", new String[] {"ST1"});
                vehicles.put("Saleen", new String[] {"S7"});
                break;
        }
        return vehicles;
    }

    private ArrayList<Supplier> getSuppliers() {
        ArrayList<Supplier> suppliers = new ArrayList<>();
        String[] supplierNames = {"Mercedes Benz", "Cadillac", "Chevrolet", "Rolls-Royce", "Jaguar", "Jeep", "Lamborghini",
                                  "Hummer", "Lincoln", "Aston Martin", "Lexus", "Maybach", "Ford", "Dodge", "Toyota",
                                  "Nissan", "Chrysler", "Kia", "Spyker", "Honda", "Mazda", "BMW", "Citroen", "Peugeot",
                                  "Bugatti","Ferrari", "Pagani", "Porsche", "Koenigsegg", "Zenvo", "Saleen", "Volkswagen",
                                  "Hyundai", "Dodge"};
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
        // {top speed, acceleration, bhp, model year, price}
        // Crossovers
        // Sedans
        // Trucks
        // Minivans
        // Hatchbacks
        // Convertibles
        // Sports cars
        // Vans
        descriptions.put("Partner", new double[] {108, 13.0, 120, 2018, 23320});
        return descriptions;
    }
}
