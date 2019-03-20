package com.codecool.shop.controller;

import com.codecool.shop.dao.LoginDao;
import com.codecool.shop.model.User;
import com.codecool.shop.dao.implementation.database.LoginDaoDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if (email != null && password != null){
            LoginDao loginDao = LoginDaoDB.getInstance();
            List<User> userList = loginDao.getUser(password);
            if (checkUser(userList, password, email)) {
                HttpSession session = req.getSession(true);
                session.setAttribute("username", userList.get(0));
                session.setAttribute("id", userList.get(0));
                resp.sendRedirect("/");
            } else {
                resp.sendRedirect("/");
            }
        } else {
            resp.sendRedirect("/");
        }

    }

    public boolean checkUser(List<User> userList, String passwordInput, String emailInput) {
        return userList.get(0).email.equals(emailInput) && userList.get(0).password.equals(passwordInput);
    }
}
