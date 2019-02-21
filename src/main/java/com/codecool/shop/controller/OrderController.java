package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.ShoppingCartDaoMem;
import com.codecool.shop.model.Address;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.PersonalInfo;
import org.json.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = {"/order"})
public class OrderController extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Getting input data JSON, and converting it to a JSONObject
        BufferedReader reader = request.getReader();
        String line = reader.readLine();
        reader.close();
        JSONObject jsonObject = new JSONObject(line);
        //Validating the sub-JSONObjects of the input
        boolean personalInfoIsValid = validatePersonalInfo(((JSONObject) jsonObject.get("personalInfo")));
        boolean shippingAddressisValid = validateAddress(((JSONObject) jsonObject.get("shippingAddress")));
        boolean billingInfoIsValid = validateAddress(((JSONObject) jsonObject.get("billingAddress")));
        //Registering the new order
        if(personalInfoIsValid && shippingAddressisValid && billingInfoIsValid){
            HttpSession session = request.getSession(true);
            String sessionId = session.getId();
            registerOrder(sessionId, jsonObject);
        }
    }

    private void registerOrder(String sessionId, JSONObject jsonObject) {
        OrderDao orders = OrderDaoMem.getInstance();
        ShoppingCartDao carts = ShoppingCartDaoMem.getInstance();
        //Getting the personal info out of the jsonObject
        JSONObject personalInfo = (JSONObject) jsonObject.get("personalInfo");
        String firstName = (String) personalInfo.get("firstName");
        String lastName = (String) personalInfo.get("lastName");
        String phoneNumber = (String) personalInfo.get("phoneNumber");
        String email = (String) personalInfo.get("email");

        PersonalInfo personalInfoObject = new PersonalInfo(firstName, lastName, email, phoneNumber);

        //Getting the shipping address out of the jsonObject
        JSONObject shippingAddress = (JSONObject) jsonObject.get("shippingAddress");
        String shippingCountry = (String) shippingAddress.get("country");
        String shippingCity = (String) shippingAddress.get("city");
        String shippingZipcode = (String) shippingAddress.get("zipcode");
        String shippingHouseAddress = (String) shippingAddress.get("address");

        Address shippingAddressObject = new Address(shippingCountry, shippingCity, Integer.parseInt(shippingZipcode), shippingHouseAddress);

        //Getting the billing address out of the jsonObject
        JSONObject billingAddress = (JSONObject) jsonObject.get("billingAddress");
        String billingCountry = (String) billingAddress.get("country");
        String billingCity = (String) billingAddress.get("city");
        String billingZipcode = (String) billingAddress.get("zipcode");
        String billingHouseAddress = (String) billingAddress.get("address");

        Address billingAddressObject = new Address(billingCountry, billingCity, Integer.parseInt(billingZipcode), billingHouseAddress);

        //Creating new order
        Order order = new Order(sessionId, personalInfoObject, carts.getShoppingCart(sessionId), billingAddressObject, shippingAddressObject);
        orders.addOrder(sessionId, order);
        orders.getOrder(sessionId);
    }

    private boolean validatePersonalInfo(JSONObject personalInfo){
        String firstName = (String) personalInfo.get("firstName");
        String lastName = (String) personalInfo.get("lastName");
        String phoneNumber = (String) personalInfo.get("phoneNumber");
        String email = (String) personalInfo.get("email");
        return (!firstName.equals("") && !lastName.equals("") && !phoneNumber.equals("") && !email.equals(""));
    }

    private boolean validateAddress(JSONObject address){
        String country = (String) address.get("country");
        String city = (String) address.get("city");
        Integer zipcode = Integer.parseInt((String) address.get("zipcode"));
        String houseAddress = (String) address.get("address");
        return (!country.equals("") && !city.equals("") && zipcode!=null && !houseAddress.equals(""));
    }
}
