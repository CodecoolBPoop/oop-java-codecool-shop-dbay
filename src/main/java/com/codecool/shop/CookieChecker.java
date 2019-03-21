package com.codecool.shop;

import com.codecool.shop.model.User;

import javax.servlet.http.Cookie;

public class CookieChecker {

    public static User checkUser(Cookie[] cookies) {
        User user = new User();
        if (cookies == null) {
            user.username = "Not logged in!";
            return user;
        }
        if (cookies.length == 1 && cookies[0].getName().equals("JSESSIONID")) {
            user.username = "Not logged in!";
        } else {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals("username")) {
                    user.username = cookies[i].getValue();
                }
            }
        }
        return user;
    }
}
