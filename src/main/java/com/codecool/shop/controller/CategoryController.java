package com.codecool.shop.controller;

import com.codecool.shop.SessionChecker;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.memory.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.memory.ProductDaoMem;
import com.codecool.shop.model.User;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/"})
public class CategoryController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();

        String[] categoriesArray = {"Crossovers", "Hatchbacks", "Sedans", "Convertibles", "Vans", "Trucks", "Minivans", "Sport cars"};
        String elements = "categories";

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        //// CHECK USER ////
        HttpSession session = req.getSession();
        User user = SessionChecker.checkUser(session);

        context.setVariable("elements", elements);
        context.setVariable("categoriesArray", categoriesArray);
        context.setVariable("userName", user.username);
        engine.process("product/index.html", context, resp.getWriter());
    }

}
