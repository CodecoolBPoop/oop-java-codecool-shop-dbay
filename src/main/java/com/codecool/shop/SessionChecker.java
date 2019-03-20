package com.codecool.shop;

import com.codecool.shop.model.User;

import javax.servlet.http.HttpSession;

public class SessionChecker {

    public static User checkUser(HttpSession session) {
        User user = new User();
        if (session.getAttribute("username") == null) {
            user.username = "Not logged in!";
        } else {
            user = (User) session.getAttribute("username");
        }
        return user;
    }
}
