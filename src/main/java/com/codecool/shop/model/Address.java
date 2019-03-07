package com.codecool.shop.model;

public class Address {
    private int id;

    private String country;

    private String city;
    private int zipcode;
    private String address;

    public Address() {
    }
    public Address(String country, String city, int zipcode, String address) {
        this.country = country;
        this.city = city;
        this.zipcode = zipcode;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWholeAddress(){
        String[] values = {String.valueOf(this.zipcode), this.country, this.city, this.address};
        return String.join(", ", values);
    }
}
