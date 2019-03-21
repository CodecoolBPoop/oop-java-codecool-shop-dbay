package com.codecool.shop.dao;

import com.codecool.shop.model.User;

import java.util.List;

public interface RegisterDao {
    void addUser(String username, String password, String email);
    List<User> getUser(String email);
}
