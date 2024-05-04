package com.restaurant.model;

import javafx.beans.property.*;

public class Order {

    private final IntegerProperty idProperty;
    private final StringProperty customerProperty;
    private final DoubleProperty totalPriceProperty;
    private final StringProperty dataProperty;

    public Order() {
        this.idProperty = new SimpleIntegerProperty();
        this.customerProperty = new SimpleStringProperty();
        this.totalPriceProperty = new SimpleDoubleProperty();
        this.dataProperty = new SimpleStringProperty();
    }

    public void setIdProperty(int idProperty) {
        this.idProperty.set(idProperty);
    }

    public void setCustomerProperty(String customerProperty) {
        this.customerProperty.set(customerProperty);
    }

    public void setTotalPriceProperty(double totalPriceProperty) {
        this.totalPriceProperty.set(totalPriceProperty);
    }

    public void setDataProperty(String dataProperty) {
        this.dataProperty.set(dataProperty);
    }

    public int getIdProperty() {
        return idProperty.get();
    }

    public IntegerProperty idPropertyProperty() {
        return idProperty;
    }

    public String getCustomerProperty() {
        return customerProperty.get();
    }

    public StringProperty customerPropertyProperty() {
        return customerProperty;
    }

    public double getTotalPriceProperty() {
        return totalPriceProperty.get();
    }

    public DoubleProperty totalPricePropertyProperty() {
        return totalPriceProperty;
    }

    public String getDataProperty() {
        return dataProperty.get();
    }

    public StringProperty dataPropertyProperty() {
        return dataProperty;
    }
}
