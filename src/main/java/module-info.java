module com.restaurant {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.restaurant to javafx.fxml;
    exports com.restaurant;
    exports com.restaurant.util;
    opens com.restaurant.util to javafx.fxml;
    exports com.restaurant.controller;
    opens com.restaurant.controller to javafx.fxml;
}