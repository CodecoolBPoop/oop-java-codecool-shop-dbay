package com.codecool.shop.dao;

import com.codecool.shop.model.User;

import java.util.List;

public interface LoginDao {
    List<User> getUser(String password);
}