package com.restaurant.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Staff {
    private final IntegerProperty idProperty;
    private final StringProperty nameProperty;
    private final StringProperty genderProperty;
    private final IntegerProperty ageProperty;
    private final StringProperty roleProperty;
    private final StringProperty salaryProperty;
    private final StringProperty dateProperty;

    public Staff() {
        this.idProperty = new SimpleIntegerProperty();
        this.nameProperty = new SimpleStringProperty();
        this.genderProperty = new SimpleStringProperty();
        this.ageProperty = new SimpleIntegerProperty();
        this.roleProperty = new SimpleStringProperty();
        this.salaryProperty = new SimpleStringProperty();
        this.dateProperty = new SimpleStringProperty();
    }

    //for id
    public int getId() {
        return idProperty.get();
    }

    public void setId(int id) {
        this.idProperty.set(id);
    }
    public IntegerProperty getStaffId() {
        return idProperty;
    }

    //for name
    public String getName() {
        return nameProperty.get();
    }

    public void setName(String name) {
        this.nameProperty.set(name);
    }
    public StringProperty getStaffName() {
        return nameProperty;
    }

    // per gender
    public String getGender() {
        return genderProperty.get();
    }

    public void setGender(String gender) {
        this.genderProperty.set(gender);
    }

    public StringProperty getStaffGender() {
        return genderProperty;
    }

    // per age
    public int getAge() {
        return ageProperty.get();
    }

    public void setAge(int age) {
        this.ageProperty.set(age);
    }

    public IntegerProperty getStaffAge() {
        return ageProperty;
    }

    // per role
    public String getRole() {
        return roleProperty.get();
    }

    public void setRole(String role) {
        this.roleProperty.set(role);
    }

    public StringProperty getStaffRole() {
        return roleProperty;
    }

    // per salary
    public String getSalary() {
        return salaryProperty.get();
    }

    public void setSalary(String salary) {
        this.salaryProperty.set(salary);
    }

    public StringProperty getStaffSalary() {
        return salaryProperty;
    }

    // per date
    public String getDate() {
        return dateProperty.get();
    }

    public void setDate(String date) {
        this.dateProperty.set(date);
    }

    public StringProperty getStaffDate() {
        return dateProperty;
    }
}
