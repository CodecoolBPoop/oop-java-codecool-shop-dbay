package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.database.ProductCategoryDaoDB;
import com.codecool.shop.dao.implementation.database.ProductDaoDB;
import com.codecool.shop.dao.implementation.database.ShoppingCartDaoDB;
import com.codecool.shop.dao.implementation.memory.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.memory.ProductDaoMem;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.implementation.memory.ShoppingCartDaoMem;
import com.codecool.shop.model.Product;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/products"})
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDaoDB products = ProductDaoDB.getInstance();
        ProductDao productDataStore = ProductDaoDB.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoDB.getInstance();

//        Map params = new HashMap<>();
//        params.put("category", productCategoryDataStore.find(1));
//        params.put("products", productDataStore.getBy(productCategoryDataStore.find(1)));

        String elements = "products";
        String style = req.getParameter("style");
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
//        context.setVariables(params);
        String[] categoriesArray = {"Crossovers", "Hatchbacks", "Sedans", "Convertibles", "Vans", "Trucks", "Minivans", "Sport cars"};

        // Get elements for the dropdown list.
        List<Product> allProducts = productDataStore.getAll();
        ArrayList<String> dropdownElements = getDropdownElements(style, allProducts);
        // Set template engine.
        context.setVariable("recipient", "World");
        context.setVariable("category", productCategoryDataStore.find(1));
        context.setVariable("products", allProducts);
        context.setVariable("elements", elements);
        context.setVariable("style", style);
        context.setVariable("categoriesArray", categoriesArray);
        context.setVariable("dropdown", dropdownElements);
        engine.process("product/index.html", context, resp.getWriter());
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int productId = Integer.parseInt(req.getParameter("addProduct"));
        HttpSession session = req.getSession(true);
        String sessionId = session.getId();
        Product product = ProductDaoDB.getInstance().find(productId);
        ShoppingCartDaoDB.getInstance().addToShoppingCart(sessionId, product);
        resp.sendRedirect("/products?style=" + product.getProductCategory().getName());
    }

    private ArrayList<String> getDropdownElements(String style, List<Product> allProducts) {
        ArrayList<String> dropdownElements = new ArrayList<>();
        for (Product product: allProducts) {
            if (product.getProductCategory().getName().equals(style)) {
                if (!dropdownElements.contains(product.getSupplier().getName()))
                    dropdownElements.add(product.getSupplier().getName());
            }
        }
        return dropdownElements;
    }
}
