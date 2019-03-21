package com.codecool.shop.dao.implementation.database;

import com.codecool.shop.dao.RegisterDao;
import com.codecool.shop.model.User;

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

    @Override
    public List<User> getUser(String email) {
        List<Object> value = new ArrayList<>();
        value.add(email);
        List<Object> userQuery = executeQuery("SELECT * FROM users WHERE email=?;", value);
        List<User> userData = new ArrayList<>();
        for (Object element: userQuery) {
            if(element instanceof User) userData.add((User) element);
        }
        return userData;
    }
}
