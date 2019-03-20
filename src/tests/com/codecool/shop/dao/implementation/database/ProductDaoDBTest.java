package com.codecool.shop.dao.implementation.database;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductDaoDBTest {
    @Test
    public void testIfFindingProductWorks(){
        ProductDao productDao = ProductDaoDB.getInstance();
        Product product = productDao.find(34);
    }

    @Test
    public void testIfAddingProductWorks(){
        Product product = new Product();
        product.setId(100);
        product.setName("Autó");
        product.setDescription("fullos nagyon");
        product.setAcceleration(1.0);
        product.setPrice(1000.0f, "CZK");
        product.setProductCategoryId(1);
        product.setSupplierId(1);
        ProductDao productDao = ProductDaoDB.getInstance();
        productDao.add(product);
    }

    @Test
    public void testIfRemovingProductWorks(){
        ProductDao productDao = ProductDaoDB.getInstance();
        productDao.remove(83);
    }

    @Test
    public void testIfFindingAllProductsWorks(){
        ProductDao productDao = ProductDaoDB.getInstance();
        List<Product> products = productDao.getAll();
    }

    @Test
    public void testIfFindingProductsBySupplierWorksIfSupplierExists(){
        SupplierDao supplierDao = SupplierDaoDB.getInstance();
        Supplier supplier = supplierDao.find(3);
        ProductDao productDao = ProductDaoDB.getInstance();
        List<Product> products = productDao.getBy(supplier);
    }

    @Test
    public void testIfFindingProductsBySupplierReturnsNullIfSupplierIsNull(){
        ProductDao productDao = ProductDaoDB.getInstance();
        List<Product> products = productDao.getBy((Supplier)null);
        assertNull(products);
    }

    @Test
    public void testIfFindingProductsBySupplierReturnsEmptyListIfSuppliersIdIsNotInDatabase(){
        Supplier supplier = new Supplier();
        supplier.setId(100);
        List<Product> products = ProductDaoDB.getInstance().getBy(supplier);
        assertEquals(new ArrayList<Product>(), products);
    }

    @Test
    public void testIfFindingProductsByProductCategoryWorks(){
        ProductCategoryDao productCategoryDao = ProductCategoryDaoDB.getInstance();
        ProductCategory productCategory = productCategoryDao.find(1);
        ProductDao productDao = ProductDaoDB.getInstance();
        List<Product> products = productDao.getBy(productCategory);
    }

    @Test
    public void testIfFindingProductsByProductCategoryReturnsNullIfProductCategoryIsNull(){
        ProductDao productDao = ProductDaoDB.getInstance();
        List<Product> products = productDao.getBy((ProductCategory) null);
        assertNull(products);
    }

    @Test
    public void testIfFindingProductsByProductCategoryReturnsEmptyListIfProductCategorysIdIsNotInDatabase(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setId(100);
        List<Product> products = ProductDaoDB.getInstance().getBy(productCategory);
        assertEquals(new ArrayList<Product>(), products);
    }
}