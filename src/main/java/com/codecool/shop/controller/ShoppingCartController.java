package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.dao.implementation.database.ShoppingCartDaoDB;
import com.codecool.shop.dao.implementation.memory.ShoppingCartDaoMem;
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

@WebServlet(urlPatterns = {"/shopping-cart"})
public class ShoppingCartController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ShoppingCartDao shoppingCartDataStore = ShoppingCartDaoDB.getInstance();

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        HttpSession session = req.getSession(true);
        String sessionId = session.getId();


        context.setVariable("cartItems", shoppingCartDataStore.getShoppingCart(sessionId));
        try {
            engine.process("product/shoppingCart.html", context, resp.getWriter());
        } catch (TemplateProcessingException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        int cartId = Integer.parseInt(req.getParameter("addProduct"));
        HttpSession session = req.getSession(true);
        String sessionId = session.getId();
        ShoppingCartDao shoppingCartDataStore = ShoppingCartDaoDB.getInstance();
//        ProductDao productStore = ProductDaoMem.getInstance();
        shoppingCartDataStore.getShoppingCart(sessionId);

        resp.sendRedirect("/shopping-cart");
    }

}
