package com.codecool.shop.dao.implementation.database;

import com.codecool.shop.dao.LoginDao;
import com.codecool.shop.model.User;

import java.util.ArrayList;
import java.util.List;

public class LoginDaoDB extends DaoDatabase implements LoginDao {
    private static LoginDaoDB instance;

    private LoginDaoDB() {}

    public static LoginDaoDB getInstance() {
        if (instance == null) {
            instance = new LoginDaoDB();
        }
        return instance;
    }

    @Override
    public List<User> getUser(String password) {
        List<Object> value = new ArrayList<>();
        value.add(password);
        List<Object> userQuery = executeQuery("SELECT * FROM users WHERE password=?;", value);
        List<User> userData = new ArrayList<>();
        for (Object element: userQuery) {
            if(element instanceof User) userData.add((User) element);
        }
        return userData;
    }
}
