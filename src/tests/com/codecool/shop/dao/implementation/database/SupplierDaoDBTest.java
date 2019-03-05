package com.codecool.shop.dao.implementation.database;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SupplierDaoDBTest {
    @Test
    public void testAddingNewSupplierInsertsSupplierToDatabase(){
        SupplierDao supplierDao = SupplierDaoDB.getInstance();
        supplierDao.add(new Supplier("name", "description"));
    }

    @Test
    public void testIsFindingSuppliersSelectsCorrectSupplierFromDatabaseAndReturnsAMappedObject(){
        SupplierDao supplierDao = SupplierDaoDB.getInstance();
        Supplier supplier = new Supplier("name", "description");
        supplier.setId(1);
        assertEquals(supplier, supplierDao.find(1));
    }
}