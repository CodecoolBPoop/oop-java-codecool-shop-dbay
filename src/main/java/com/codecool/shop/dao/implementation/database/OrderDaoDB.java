package com.codecool.shop.dao.implementation.database;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.model.Address;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.PersonalInfo;
import com.codecool.shop.model.ShoppingCart;

import java.util.ArrayList;
import java.util.List;

public class OrderDaoDB extends DaoDatabase implements OrderDao {

    private class AddressDaoDB extends DaoDatabase {
        public Address getAddress(int addressID){
            Address address = null;
            List<Object> values = new ArrayList<>();
            values.add(addressID);

            try{
                address = (Address) executeQuery("SELECT * FROM Addresses WHERE id=?;", values).get(0);
            } catch(Exception e){}

            return address;
        }

        public Address addAddress(Address address){
            List<Object> values = new ArrayList<>();
            values.add(address.getCountry());
            values.add(address.getCity());
            values.add(address.getZipcode());
            values.add(address.getAddress());
            return (Address) executeQuery("INSERT INTO addresses (country, city, zipcode, address) VALUES (?, ?, ?, ?) RETURNING *;", values).get(0);
        }

        public void deleteAddress(int addressID){
            List<Object> values = new ArrayList<>();
            values.add(addressID);
            executeQuery("DELETE FROM addresses WHERE id=?;", values);
        }
    }

    private class PersonalInfoDaoDB extends DaoDatabase {
        public PersonalInfo getPersonalInfo(int personalInfoID){
            PersonalInfo personalInfo = null;
            List<Object> values = new ArrayList<>();
            values.add(personalInfoID);

            try{
                personalInfo = (PersonalInfo) executeQuery("SELECT * FROM personal_info WHERE id=?;", values).get(0);
            } catch (Exception e) {}

            return personalInfo;
        }

        public PersonalInfo addPersonalInfo(PersonalInfo personalInfo){
            List<Object> values = new ArrayList<>();
            values.add(personalInfo.getFirstName());
            values.add(personalInfo.getLastName());
            values.add(personalInfo.getEmail());
            values.add(personalInfo.getPhoneNumber());
            return (PersonalInfo) executeQuery("INSERT INTO personal_info (\"firstName\", \"lastName\",  \"email\", \"phoneNumber\") VALUES (?, ?, ?, ?) RETURNING *;", values).get(0);
        }

        public void deletePersonalInfo (int personalInfoID){}
    }

    private static OrderDaoDB instance;

    private OrderDaoDB() {
    }

    public static OrderDaoDB getInstance(){
        if(instance==null){
            instance=new OrderDaoDB();
        }
        return instance;
    }

    @Override
    public Order getOrder(String sessionId) {
        Order order = null;
        ShoppingCartDao shoppingCartDao = ShoppingCartDaoDB.getInstance();
        ShoppingCart cart = shoppingCartDao.getShoppingCart(sessionId);
        AddressDaoDB addressDaoDB = new AddressDaoDB();
        Address shippingAddress = null;
        Address billingAddress = null;
        PersonalInfoDaoDB personalInfoDaoDB = new PersonalInfoDaoDB();
        PersonalInfo personalInfo = null;

        List<Object> values = new ArrayList<>();
        values.add(sessionId);

        try {
            order = (Order) executeQuery("SELECT * FROM orders WHERE \"sessionId\" = ?", values).get(0);
        } catch (Exception e) {}

        if(order!=null){
            shippingAddress = addressDaoDB.getAddress(order.getShippingAddressId());
            billingAddress = addressDaoDB.getAddress(order.getBillingAddressId());
            personalInfo = personalInfoDaoDB.getPersonalInfo(order.getPersonalInfoId());

            order.setBillingAddress(billingAddress);
            order.setShippingAddress(shippingAddress);
            order.setCart(cart);
            order.setPersonalInfo(personalInfo);
        }

        return order;
    }

    @Override
    public void deleteOrder(String sessionId) {
        Order order = getOrder(sessionId);

        if(order!=null){
            List<Object> values = new ArrayList<>();

            values.add(sessionId);
            executeQuery("DELETE FROM personal_info WHERE \"sessionId\"=?", values);
            values.clear();

            values.add(order.getShippingAddressId());
            values.add(order.getBillingAddressId());
            executeQuery("DELETE FROM addresses WHERE id=? OR id=?", values);
            values.clear();

            values.add(order.getCartId());
            executeQuery("DELETE FROM line_items WHERE \"cartId\"=?", values);
            values.clear();

            values.add(order.getCartId());
            executeQuery("DELETE FROM shopping_carts WHERE id=?", values);
            values.clear();

            values.add(sessionId);
            executeQuery("DELETE FROM Orders WHERE \"sessionId\"=?", values);
        }
    }

    @Override
    public void addOrder(String sessionId, Order order) {
        List<Object> values = new ArrayList<>();

        AddressDaoDB addressDao = new AddressDaoDB();
        PersonalInfoDaoDB personalInfoDao = new PersonalInfoDaoDB();

        //Adds data to the database, which returns the actual state of the data(actual id assigned by the table),
        //then sets the field so it will be the same in the object as it is in the DB
        order.setShippingAddress(addressDao.addAddress(order.getShippingAddress()));
        order.setBillingAddress(addressDao.addAddress(order.getBillingAddress()));
        order.setPersonalInfo(personalInfoDao.addPersonalInfo(order.getPersonalInfo()));

        values.add(order.getPersonalInfoId());
        values.add(order.getShippingAddressId());
        values.add(order.getBillingAddressId());
        values.add(order.getCartId());
        values.add(order.getSessionId());
        executeQuery("INSERT INTO Orders (\"personalInfoId\", \"shippingAddressId\", \"billingAddressId\", \"cartId\", \"sessionId\") VALUES (?, ?, ?, ?, ?)", values);
    }

    @Override
    public void updateOrder(String sessionId, Order order) {

    }
}
