package com.restaurant.model;

import javafx.beans.property.*;

public class Dish {

    private final IntegerProperty idProperty;
    private final StringProperty dishProperty;
    private final StringProperty courseProperty;
    private final DoubleProperty priceProperty;
    private final StringProperty dateProperty;

    public Dish(){
        this.idProperty = new SimpleIntegerProperty();
        this.dishProperty = new SimpleStringProperty();
        this.courseProperty = new SimpleStringProperty();
        this.priceProperty = new SimpleDoubleProperty();
        this.dateProperty = new SimpleStringProperty();
    }

    //for id

    public int getId() {
        return idProperty.get();
    }

    public IntegerProperty getDishId() {
        return idProperty;
    }

    public void setId(int id) {
        this.idProperty.set(id);
    }

    //for dish name

    public String getDish() {
        return dishProperty.get();
    }

    public StringProperty getDishName() {
        return dishProperty;
    }

    public void setDish(String dish) {
        this.dishProperty.set(dish);
    }

    //for course

    public String getCourse() {
        return courseProperty.get();
    }

    public StringProperty getDishCourse() {
        return courseProperty;
    }

    public void setCourse(String course) {
        this.courseProperty.set(course);
    }

    //for price

    public double getPrice() {
        return priceProperty.get();
    }

    public DoubleProperty getDishPrice() {
        return priceProperty;
    }

    public void setPrice(double price) {
        this.priceProperty.set(price);
    }

    //for date

    public String getDate() {
        return dateProperty.get();
    }

    public StringProperty getDishDate() {
        return dateProperty;
    }

    public void setDate(String date) {
        this.dateProperty.set(date);
    }
}
