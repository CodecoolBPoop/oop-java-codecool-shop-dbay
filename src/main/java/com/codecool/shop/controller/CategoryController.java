package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.database.ProductCategoryDaoDB;
import com.codecool.shop.dao.implementation.database.ProductDaoDB;
import com.codecool.shop.dao.implementation.memory.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.memory.ProductDaoMem;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/"})
public class CategoryController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDataStore = ProductDaoDB.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoDB.getInstance();

//        Map params = new HashMap<>();
//        params.put("category", productCategoryDataStore.find(1));
//        params.put("products", productDataStore.getBy(productCategoryDataStore.find(1)));

        String[] categoriesArray = {"Crossovers", "Hatchbacks", "Sedans", "Convertibles", "Vans", "Trucks", "Minivans", "Sport cars"};
        String elements = "categories";

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
//        context.setVariables(params);


        context.setVariable("elements", elements);
        context.setVariable("categoriesArray", categoriesArray);
        engine.process("product/index.html", context, resp.getWriter());
    }

}
