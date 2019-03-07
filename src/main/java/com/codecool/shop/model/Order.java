package com.codecool.shop.model;

public class Order {
    private int id;
    private String sessionId;
    private PersonalInfo personalInfo;
    private Address billingAddress;
    private Address shippingAddress;
    private ShoppingCart cart;

    private int personalInfoID;
    private int billingAddressID;
    private int shippingAddressID;
    private int cartID;

    public Order() {
    }

    public Order(String sessionId, PersonalInfo personalInfo, ShoppingCart cart, Address billingAddress, Address shippingAddress) {
        this.sessionId = sessionId;
        this.personalInfo = personalInfo;
        this.cart = cart;
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPersonalInfoID() {
        return personalInfoID;
    }

    public void setPersonalInfoID(int personalInfoID) {
        this.personalInfoID = personalInfoID;
    }

    public int getBillingAddressID() {
        return billingAddressID;
    }

    public void setBillingAddressID(int billingAddressID) {
        this.billingAddressID = billingAddressID;
    }

    public int getShippingAddressID() {
        return shippingAddressID;
    }

    public void setShippingAddressID(int shippingAddressID) {
        this.shippingAddressID = shippingAddressID;
    }

    public int getCartID() {
        return cartID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }

    public PersonalInfo getPersonalInfo() {
        return personalInfo;
    }

    public void setPersonalInfo(PersonalInfo personalInfo) {
        this.personalInfo = personalInfo;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }
}
