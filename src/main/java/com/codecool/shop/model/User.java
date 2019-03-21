package com.codecool.shop.model;

public class User {
    public int id;
    public String username;
    public String password;
    public String email;

    public User() {}

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
