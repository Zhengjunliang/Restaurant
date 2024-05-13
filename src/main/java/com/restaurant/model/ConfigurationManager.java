package com.restaurant.model;

public class ConfigurationManager {

    private static ConfigurationManager instance;

    private String username;
    private String dbUrl;
    private String dbUsername;
    private String dbPassword;
    private double price;

    private ConfigurationManager() {
        this.username = "";
        this.dbUrl = "jdbc:mysql://127.0.0.1:3306/db.restaurant";
        this.dbUsername = "root";
        this.dbPassword = "root";
        this.price = 0.0;
    }

    public static ConfigurationManager getInstance() {
        if (instance == null) {
            instance = new ConfigurationManager();
        }
        return instance;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setDbUsername(String dbUsername) {
        this.dbUsername = dbUsername;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    public String getDbUsername() {
        return dbUsername;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
