package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.dao.implementation.database.ShoppingCartDaoDB;
import com.codecool.shop.dao.implementation.memory.ShoppingCartDaoMem;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.ShoppingCart;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.exceptions.TemplateProcessingException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/shopping-cart-size"})
public class ShoppingCartSizeController extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(true);
        String sessionId = session.getId();
        ShoppingCartDao shoppingCartDataStore = ShoppingCartDaoDB.getInstance();
        ShoppingCart shoppingCart = shoppingCartDataStore.getShoppingCart(sessionId);
        int shoppingCartSize=0;
        if(shoppingCart!=null){
            for (LineItem lineItem: shoppingCart.getLineItems()) {
                shoppingCartSize+=lineItem.getQuantity();
            }
        }

        String json = "{\"cartSize\": " + shoppingCartSize +"}";
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().append(json);
    }

}
