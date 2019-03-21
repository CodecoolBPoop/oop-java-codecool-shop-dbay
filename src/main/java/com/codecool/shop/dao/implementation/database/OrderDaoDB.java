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

        public void addAddress(Address address){}

        public void deleteAddress(int addressID){}
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

        public void addPersonalInfo(PersonalInfo personalInfo){}

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
            shippingAddress = addressDaoDB.getAddress(order.getShippingAddressID());
            billingAddress = addressDaoDB.getAddress(order.getBillingAddressID());
            personalInfo = personalInfoDaoDB.getPersonalInfo(order.getPersonalInfoID());

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

            values.add(order.getShippingAddressID());
            values.add(order.getBillingAddressID());
            executeQuery("DELETE FROM addresses WHERE id=? OR id=?", values);
            values.clear();

            values.add(order.getCartID());
            executeQuery("DELETE FROM line_items WHERE \"cartId\"=?", values);
            values.clear();

            values.add(order.getCartID());
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

        addressDao.addAddress(order.getShippingAddress());
        addressDao.addAddress(order.getBillingAddress());
        personalInfoDao.addPersonalInfo(order.getPersonalInfo());

        values.add(order.getPersonalInfoID());
        values.add(order.getShippingAddressID());
        values.add(order.getBillingAddressID());
        values.add(order.getCartID());
        values.add(order.getSessionId());
        executeQuery("INSERT INTO Orders (\"personalInfoId\", \"shippingAddressId\", \"billingAddressId\", \"cartId\", \"sessionId\") VALUES (?, ?, ?, ?, ?)", values);
    }

    @Override
    public void updateOrder(String sessionId, Order order) {

    }
}
