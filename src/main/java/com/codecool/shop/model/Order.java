package com.codecool.shop.model;

public class Order {
    private int id;
    private String sessionId;
    private PersonalInfo personalInfo;
    private Address billingAddress;
    private Address shippingAddress;
    private ShoppingCart cart;

    private int personalInfoId;
    private int billingAddressId;
    private int shippingAddressId;
    private int cartId;

    public Order() {
    }

    public Order(String sessionId, PersonalInfo personalInfo, ShoppingCart cart, Address billingAddress, Address shippingAddress) {
        this.sessionId = sessionId;
        this.personalInfo = personalInfo;
        this.personalInfoId = personalInfo.getId();
        this.cart = cart;
        this.cartId = cart.getId();
        this.billingAddress = billingAddress;
        this.billingAddressId = billingAddress.getId();
        this.shippingAddress = shippingAddress;
        this.shippingAddressId = shippingAddress.getId();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPersonalInfoId() {
        return personalInfoId;
    }

    public void setPersonalInfoId(int personalInfoId) {
        this.personalInfoId = personalInfoId;
    }

    public int getBillingAddressId() {
        return billingAddressId;
    }

    public void setBillingAddressId(int billingAddressId) {
        this.billingAddressId = billingAddressId;
    }

    public int getShippingAddressId() {
        return shippingAddressId;
    }

    public void setShippingAddressId(int shippingAddressId) {
        this.shippingAddressId = shippingAddressId;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public PersonalInfo getPersonalInfo() {
        return personalInfo;
    }

    public void setPersonalInfo(PersonalInfo personalInfo) {
        this.personalInfo = personalInfo;
        this.personalInfoId = personalInfo.getId();
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
        this.billingAddressId = billingAddress.getId();
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
        this.shippingAddressId = shippingAddress.getId();
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
        this.cartId = cart.getId();
    }
}
