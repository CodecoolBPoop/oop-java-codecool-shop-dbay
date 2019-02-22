package com.codecool.shop.model;

public class Order {
    private String sessionId;
    private PersonalInfo personalInfo;
    private Address billingAddress;
    private Address shippingAddress;
    private ShoppingCart cart;

    public Order() {
    }

    public Order(String sessionId, PersonalInfo personalInfo, ShoppingCart cart, Address billingAddress, Address shippingAddress) {
        this.sessionId = sessionId;
        this.personalInfo = personalInfo;
        this.cart = cart;
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
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
