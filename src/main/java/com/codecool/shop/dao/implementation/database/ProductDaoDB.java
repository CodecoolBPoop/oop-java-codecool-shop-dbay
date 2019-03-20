package com.codecool.shop.dao.implementation.database;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductDaoDB extends DaoDatabase implements ProductDao {

    private static ProductDaoDB instance;

    private ProductDaoDB() {}

    public static ProductDaoDB getInstance() {
        if (instance == null) {
            instance = new ProductDaoDB();
        }
        return instance;
    }

    @Override
    public void add(Product product) {
        List<Object> values = new ArrayList<>();
        values.add(product.getName());
        values.add(product.getDescription());
        values.add(product.getDefaultPrice());
        values.add(product.getDefaultCurrency());
        values.add(product.getProductCategoryId());
        values.add(product.getSupplierId());
        values.add(product.getBhp());
        values.add(product.getAcceleration());
        values.add(product.getModelYear());
        values.add(product.getTopSpeed());
        executeQuery("INSERT INTO Products (\"name\", \"description\", \"defaultPrice\", \"defaultCurrency\", \"productCategoryId\", \"supplierId\", \"bhp\", \"acceleration\", \"modelYear\", \"topSpeed\") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);", values);
    }

    @Override
    public Product find(int id) {
        List<Object> values = new ArrayList<>();
        values.add(id);
        Product product = null;
        try{
            product = (Product) executeQuery("SELECT * FROM Products WHERE id=?;", values).get(0);
            product.setProductCategory(ProductCategoryDaoDB.getInstance().find(product.getProductCategoryId()));
            product.setSupplier(SupplierDaoDB.getInstance().find(product.getSupplierId()));
        } catch (IndexOutOfBoundsException e){
        }
        return product;
    }

    @Override
    public void remove(int id) {
        List<Object> values = new ArrayList<>();
        values.add(id);
        executeQuery("DELETE FROM Products WHERE id=?;", values);
    }

    @Override
    public List<Product> getAll() {
        SupplierDaoDB supplierDaoDB = SupplierDaoDB.getInstance();
        ProductCategoryDaoDB productCategoryDaoDB = ProductCategoryDaoDB.getInstance();
        ///////////////////////////////////////////////////////////////////////////////
        List<Object> values = executeQuery("SELECT * FROM Products;", null);
        List<Product> products = castObjectsToProducts(values);
        List<Supplier> suppliers = supplierDaoDB.getAll();
        List<ProductCategory> productCategories = productCategoryDaoDB.getAll();
        ///////////////////////////////////////////////////////////////////////
        for (Product product: products) {
            for (Supplier supplier: suppliers) {
                if(supplier.getId() == product.getSupplierId()) {
                    product.setSupplier(supplier);
                    DecimalFormat df = new DecimalFormat("#.0");
                    product.setAcceleration(Double.parseDouble(df.format(product.getAcceleration())));
                    break;
                }
            }
            for (ProductCategory productCategory : productCategories) {
                if (productCategory.getId() == product.getProductCategoryId()) {
                    product.setProductCategory(productCategory);
                    break;
                }
            }
        }
        return products;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        if(supplier==null) return null;
        List<Object> values = new ArrayList<>();
        values.add(supplier.getId());
        List<Object> objects = executeQuery("SELECT * FROM Products WHERE \"supplierId\"=?;", values);
        if(objects == null) return null;
        List<Product> products = castObjectsToProducts(objects);
        for (Product product: products) {
            product.setSupplier(supplier);
        }
        findAndSetSuppliersAndCategories(products);
        return products;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        if(productCategory==null) return null;
        List<Object> values = new ArrayList<>();
        values.add(productCategory.getId());
        List<Object> objects = executeQuery("SELECT * FROM Products WHERE \"productCategoryId\"=?;", values);
        List<Product> products = castObjectsToProducts(objects);
        for (Product product: products) {
            product.setProductCategory(productCategory);
        }
        findAndSetSuppliersAndCategories(products);
        return products;
    }

    private void findAndSetSuppliersAndCategories(List<Product> products) {
        for (Product product: products) {
            if(product.getSupplier()==null) product.setSupplier(SupplierDaoDB.getInstance().find(product.getSupplierId()));
            if(product.getProductCategory()==null) product.setProductCategory(ProductCategoryDaoDB.getInstance().find(product.getProductCategoryId()));
        }
    }

    private List<Product> castObjectsToProducts(List<Object> uncastedProducts) {
        List<Product> productList = new ArrayList<>();
        for (Object value : uncastedProducts) {
            if (value instanceof Product) productList.add((Product) value);
        }
        return productList;
    }
}
