package com.codecool.shop.model;


import java.lang.reflect.Field;

public class BaseModel {

    protected int id;
    protected String name;
    protected String description;
//    protected int bhp;
//    protected double acceleration;
//    protected int modelYear;
//    protected int topSpeed;


    public BaseModel() {
    }

    public BaseModel(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public BaseModel(String name, String description, double acceleration, int topSpeed,int bhp, int modelYear) {
        this.name = name;
        this.description = description;
//        this.bhp = bhp;
//        this.acceleration = acceleration;
//        this.modelYear = modelYear;
//        this.topSpeed = topSpeed;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (Field field : this.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object value = null;
            try {
                value = field.get(this);
                if (value != null) {
                    sb.append(field.getName() + ":" + value + ",");
                }
            } catch (IllegalAccessException e) {

            }
        }
        return sb.toString();
    }

    // GETTERS
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
//    public int getModelYear() { return modelYear; }
//    public double getAcceleration() { return acceleration; }
//    public int getBhp() { return bhp; }
//    public int getTopSpeed() { return topSpeed; }
    // SETTERS
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
//    public void setModelYear(int modelYear) { this.modelYear = modelYear; }
//    public void setBhp(int bhp) { this.bhp = bhp; }
//    public void setAcceleration(int acceleration) { this.acceleration = acceleration; }
//    public void setTopSpeed(int topSpeed) { this.topSpeed = topSpeed; }
}
