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
                break;
            case "Sedans":
                vehicles.put("Aston Martin", new String[] {"DB11", "DB4", "DB9"});
                vehicles.put("Jaguar", new String[] {"XF", "XJ"});
                vehicles.put("Lamborghini", new String[] {"Estoque", "Huracan"});
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
                break;
        }
        return vehicles;
    }

    private ArrayList<Supplier> getSuppliers() {
        ArrayList<Supplier> suppliers = new ArrayList<>();
        String[] supplierNames = {"Mercedes Benz", "Cadillac", "Chevrolet", "Rolls-Royce", "Jaguar", "Jeep", "Lamborghini",
                                  "Hummer", "Lincoln", "Aston Martin", "Lexus", "Maybach", "Ford", "Dodge", "Toyota",
                                  "Nissan", "Chrysler", "Kia", "Spyker", "Honda", "Mazda", "BMW"};
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
        descriptions.put("G-Class", new double[] {137, 4.4, 536, 2017, 123600});
        descriptions.put("ML350", new double[] {140, 6.8, 254, 2015, 50700});
        descriptions.put("GLE", new double[] {155, 5.5, 384, 2019, 55700});
        descriptions.put("Escalade", new double[] {130, 3.9, 403, 2019, 74285});
        descriptions.put("XT5", new double[] {125, 6.6, 310, 2019, 42600});
        descriptions.put("Traverse", new double[] {130, 6.7, 281, 2018, 42900});
        descriptions.put("Tahoe", new double[] {121, 5.7, 420, 2019, 52900});
        descriptions.put("Cullinan", new double[] {155, 6.7, 563, 2018, 325000});
        descriptions.put("F-Pace", new double[] {176, 6.4, 247, 2018, 77990});
        descriptions.put("Grand Cherokee", new double[] {180, 7.6, 188, 2017, 33200});
        descriptions.put("Urus", new double[] {190, 3.5, 640, 2018, 200000});
        descriptions.put("H2", new double[] {70, 7.6, 392, 2007, 19750});
        descriptions.put("Navigator", new double[] {100, 4.8, 450, 2019, 73250});
        // Sedans
        descriptions.put("DB11", new double[] {208, 3.9, 630, 2016, 157900});
        descriptions.put("DB4", new double[] {139, 9.0, 240, 1958, 1200000});
        descriptions.put("DB9", new double[] {186, 4.5, 450, 2004, 140000});
        descriptions.put("XF", new double[] {155, 5.1, 374, 2015, 58900});
        descriptions.put("XJ", new double[] {174, 4.2, 566, 2010, 75700});
        descriptions.put("Estoque", new double[] {200, 2.9, 560, 2008, 230000});
        descriptions.put("Huracan", new double[] {201, 2.3, 631, 2018, 274390});
        descriptions.put("LS 500 Sport", new double[] {157, 4.5, 416, 2018, 115675});
        descriptions.put("LS 500h", new double[] {136, 5.3, 354, 2018, 81300});
        descriptions.put("A-Class", new double[] {139, 4.5, 301, 2019, 33495});
        descriptions.put("E300", new double[] {130, 6.2, 241, 2019, 59800});
        descriptions.put("Phantom", new double[] {155, 5.5, 453, 2019, 450000});
        descriptions.put("Silver Ghost", new double[] {80, 17.0 , 86, 1925, 147900});
        descriptions.put("Ghost", new double[] {155, 4.9, 563, 2019, 636020});
        descriptions.put("s600", new double[] {130, 3.7, 493, 2017, 353760});
        // Trucks
        descriptions.put("Raptor", new double[] {127, 5.1, 493, 2017, 49520});
        descriptions.put("Ranger", new double[] {106, 13.5, 200, 2019, 30244});
        descriptions.put("Silverado", new double[] {130, 7.7, 420, 2017, 56600});
        descriptions.put("Ram", new double[] {120, 9.0, 395, 2016, 31695});
        descriptions.put("Hilux", new double[] {105, 11.5, 164, 2016, 20990});
        descriptions.put("Tacoma", new double[] {115, 9.8, 254, 2019, 33500});
        descriptions.put("Frontier", new double[] {125, 8.0, 152, 2019, 18990});
        descriptions.put("Titan", new double[] {120, 7.0, 284, 2018, 34000});
        // Minivans
        descriptions.put("Pacifica", new double[] {115, 8.5, 287, 2017, 28390});
        descriptions.put("Sedona", new double[] {110, 7.4, 177, 2019, 27200});
        descriptions.put("Grand Caravan", new double[] {126, 10.1, 283 , 2015, 33000});
        descriptions.put("Town & Country", new double[] {117, 7.7, 260 , 2017, 27100});
        descriptions.put("Sienna", new double[] {113, 6.9, 265, 2019, 31115});
        // Hatchbacks
        // Convertibles
        descriptions.put("C8", new double[] {199, 3.7, 444, 2009, 354900});
        descriptions.put("S2000", new double[] {149, 6.3, 240, 2009, 35500});
        descriptions.put("S550", new double[] {186, 3.9, 621, 2017, 149550});
        descriptions.put("C300", new double[] {155, 5.8, 362, 2018, 40250});
        descriptions.put("MX5", new double[] {130, 6.6, 181, 2018, 34190});
        descriptions.put("S63", new double[] {186, 4.2, 603, 2018, 393910});
        descriptions.put("M4", new double[] {148, 4.1, 425, 2018, 76500});
        descriptions.put("CL180", new double[] {110, 6.8, 156, 2014, 35000});
        descriptions.put("M6", new double[] {155, 4.0, 550, 2013, 130000});
        // Sports cars
        // Vans
        return descriptions;
    }
}
