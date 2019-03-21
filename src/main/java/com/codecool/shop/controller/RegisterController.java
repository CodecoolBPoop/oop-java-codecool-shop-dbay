package com.codecool.shop.controller;

import com.codecool.shop.dao.RegisterDao;
import com.codecool.shop.dao.implementation.database.RegisterDaoDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/register"})
public class RegisterController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if (email != null && password != null && username != null) {
            RegisterDao registerDao = RegisterDaoDB.getInstance();
            registerDao.addUser(username,password,email);
            Cookie usernameCookie = new Cookie("username", username);
            usernameCookie.setMaxAge(60*60*24*365);
            resp.addCookie(usernameCookie);
            resp.sendRedirect("/");
        } else {
            resp.sendRedirect("/");
        }
    }
}

