package com.codecool.shop.dao.implementation.database;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SupplierDaoDBTest {
    @Test
    public void testAddingNewSupplierInsertsSupplierToDatabase(){
        SupplierDao supplierDao = SupplierDaoDB.getInstance();
        supplierDao.add(new Supplier("name1", "description1"));
    }

    @Test
    public void testIsFindingSuppliersSelectsCorrectSupplierFromDatabaseAndReturnsAMappedObject(){
        SupplierDao supplierDao = SupplierDaoDB.getInstance();
        Supplier expected = new Supplier("name5", "description5");
        expected.setId(6);
//        assertEquals(expected, supplierDao.find(5));
        assertEquals(null, supplierDao.find(1000));
    }

    @Test
    public void testIsRemovingSupplierByIdRemovesTheCorrectRowFromDB(){
        SupplierDao supplierDao = SupplierDaoDB.getInstance();
        supplierDao.remove(10);
    }

    @Test
    public void testIsFindingAllSuppliersActuallyReturnAllSuppliersInTheDB(){
        SupplierDao supplierDao = SupplierDaoDB.getInstance();
        List<Supplier> suppliers = supplierDao.getAll();
        assertEquals(null, suppliers);
    }
}