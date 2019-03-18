//package com.codecool.shop.config;
//
//import com.codecool.shop.dao.ProductCategoryDao;
//import com.codecool.shop.dao.ProductDao;
//import com.codecool.shop.dao.SupplierDao;
//import com.codecool.shop.dao.implementation.database.DaoDatabase;
//import com.codecool.shop.dao.implementation.database.ProductCategoryDaoDB;
//import com.codecool.shop.dao.implementation.database.ProductDaoDB;
//import com.codecool.shop.dao.implementation.database.SupplierDaoDB;
//import com.codecool.shop.dao.implementation.memory.ProductCategoryDaoMem;
//import com.codecool.shop.dao.implementation.memory.ProductDaoMem;
//import com.codecool.shop.dao.implementation.memory.SupplierDaoMem;
//import com.codecool.shop.model.Product;
//import com.codecool.shop.model.ProductCategory;
//import com.codecool.shop.model.Supplier;
//
//import javax.servlet.ServletContextEvent;
//import javax.servlet.ServletContextListener;
//import javax.servlet.annotation.WebListener;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@WebListener
//public class Initializer implements ServletContextListener {}
//
//    @Override
//    public void contextInitialized(ServletContextEvent sce) {
//        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
//        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
//
//        // Add suppliers to data store.
//        ArrayList<Supplier> suppliers = getSuppliers();
//        for (Supplier supplier : suppliers) {
//            supplierDataStore.add(supplier);
//        }
//        // Add Product Categories to data store.
//        ArrayList<ProductCategory> vehicleProductCategories = getProductCategories();
//        for (ProductCategory vehicleCategory: vehicleProductCategories) {
//            productCategoryDataStore.add(vehicleCategory);
//        }
//        // Setting up products and printing them.
//        createProducts(suppliers, vehicleProductCategories);
////        for (String style : categories) {
////            for (ProductCategory category: vehicleProductCategories) {
////                if (category.getName().equals(style)) {
////                    findVehicle(productDataStore, suppliers, style, category ,descriptions);
////                    break;
////                }
////            }
////        }
//    }
//
////    private void findVehicle(ProductDao productDataStore, ArrayList<Supplier> suppliers, String style, ProductCategory category, Map<String, double[]> descriptions) {
////        Map<String, String[]> vehicles = getVehicles(style);
////        for (Map.Entry<String, String[]> element : vehicles.entrySet())
////        {
////            for (Supplier supplier: suppliers) {
////                if (supplier.getName().equals(element.getKey())) {
////                    findVehicleName(productDataStore, category, element, supplier, descriptions);
////                    break;
////                }
////            }
////        }
////    }
////
////    private void findVehicleName(ProductDao productDataStore, ProductCategory category, Map.Entry<String, String[]> element, Supplier supplier, Map<String, double[]> descriptions) {
////        double[] description = {0.0,0.0,0.0};
////        for (String car: element.getValue()) {
////            for (Map.Entry<String, double[]> desc : descriptions.entrySet()) {
////                if (desc.getKey().equals(car)) {
////                    description = desc.getValue();
////                    break;
////                }
////            }
////            String carName = element.getKey() + " " + car;
////            productDataStore.add(new Product(carName, (int) description[0], description[1],(int) description[2],(int) description[3], (float) description[4], "USD", "Placeholder desc", category, supplier));
////        }
////    }
//
//
////    private Map<String, String[]> getVehicles(String productCategory) {
//////        DaoDatabase productData = ProductDaoDB.getInstance();
//////        ((ProductDaoDB) productData).getBy(productCategory)
//////        return vehicles;
////    }
//
//    private ArrayList<Supplier> getSuppliers() {
//        SupplierDaoDB supplierData = SupplierDaoDB.getInstance();
//        ArrayList<Supplier> suppliers = new ArrayList<>();
//        List<Supplier> supplierNames = supplierData.getAll();
//        for (Supplier name: supplierNames) {
//            Supplier supplier = new Supplier(name.getName(), "Placeholder");
//            suppliers.add(supplier);
//        }
//        return suppliers;
//    }
//
//    private ArrayList<ProductCategory> getProductCategories() {
//        ProductCategoryDaoDB categoryDaoDB = ProductCategoryDaoDB.getInstance();
//        ArrayList<ProductCategory> categoriesList = new ArrayList<>();
//        List<ProductCategory> productCategories = categoryDaoDB.getAll();
//        for (ProductCategory name: productCategories) {
//            ProductCategory productCategory = new ProductCategory(name.getName(), "Placeholder", "Placeholder");
//            categoriesList.add(productCategory);
//        }
//        return categoriesList;
//    }
//
//    private void createProducts(ArrayList<Supplier> suppliers, ArrayList<ProductCategory> vehicleProductCategories) {
//        ProductDao productDataStore = ProductDaoMem.getInstance();
//        ///////////////Get all products/////////////////
//        ProductDaoDB productDaoDB = ProductDaoDB.getInstance();
//        ArrayList<Product> productsList = new ArrayList<>();
//        List<Product> products = productDaoDB.getAll();
//        ///////////////Create all products/////////////////
//        for (Product product: products) {
//            product.
//        }
//        for (ProductCategory category: vehicleProductCategories) {
//            int categoryID = category.getId();
//            for (Supplier supplier: suppliers) {
//                if ()
//            }
//        }
//
//
//    }
////    private Map<String, double[]> getDescriptions() {
////        Map<String, double[]> descriptions = new HashMap<>();
////        // {top speed, acceleration, bhp, model year, price}
////        // Crossovers
////        // Sedans
////        // Trucks
////        // Minivans
////        // Hatchbacks
////        // Convertibles
////        // Sports cars
////        // Vans
////        descriptions.put("Partner", new double[] {108, 13.0, 120, 2018, 23320});
////        return descriptions;
////    }
//}
