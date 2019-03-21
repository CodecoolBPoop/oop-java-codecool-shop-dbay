package com.codecool.shop.model;

public class PersonalInfo {
    private int id;

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    public PersonalInfo() {
    }

    public PersonalInfo(String firstName, String lastName, String email, String phoneNumber) {
        this.id = 0;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFullName(){
        return this.firstName +  " " + this.lastName;
    }

    public void setFullName(String fullName){
        String[] names = fullName.split(" ");
        this.firstName = names[0];
        this.lastName = names[1];
    }
}
