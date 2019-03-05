package com.codecool.shop.dao.implementation.database;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import java.util.ArrayList;
import java.util.List;

public class SupplierDaoDB extends DaoDatabase implements SupplierDao {
    private static SupplierDaoDB instance;

    private SupplierDaoDB(){
    }

    public static SupplierDaoDB getInstance(){
        if(instance == null){
            instance = new SupplierDaoDB();
        }
        return instance;
    }

    @Override
    public void add(Supplier supplier) {
        List<Object> values = new ArrayList<>();
        values.add(supplier.getName());
        values.add(supplier.getDescription());
        executeQuery("INSERT INTO Suppliers (name, description) VALUES (?, ?);", values);
    }

    @Override
    public Supplier find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Supplier> getAll() {
        return null;
    }
}
