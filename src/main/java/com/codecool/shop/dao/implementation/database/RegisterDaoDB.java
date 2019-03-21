package com.codecool.shop.dao.implementation.database;

import com.codecool.shop.dao.RegisterDao;

import java.util.ArrayList;
import java.util.List;

public class RegisterDaoDB extends DaoDatabase implements RegisterDao {
    private static RegisterDaoDB instance;

    private RegisterDaoDB() {}

    public static RegisterDaoDB getInstance() {
        if (instance == null) {
            instance = new RegisterDaoDB();
        }
        return instance;
    }

    @Override
    public void addUser(String username, String password, String email) {
        List<Object> values = new ArrayList<>();
        values.add(username);
        values.add(password);
        values.add(email);
        executeQuery("INSERT INTO users (username, password, email) VALUES (?, ?, ?);", values);
    }
}
