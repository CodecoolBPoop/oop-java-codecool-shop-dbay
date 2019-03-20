package com.codecool.shop;

import com.codecool.shop.model.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

public class CookieChecker {

    public static User checkUser(Cookie[] cookies) {
        User user = new User();
        if (cookies == null) {
            user.username = "Not logged in!";
            return user;
        }
        if (cookies.length == 0) {
            user.username = "Not logged in!";
        } else {
            user.username = cookies[0].getValue();
        }
        return user;
    }
}
