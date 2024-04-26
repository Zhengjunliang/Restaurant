module com.restaurant {
    requires javafx.controls;
    requires javafx.fxml;
    requires mysql.connector.j;
    requires java.sql;


    opens com.restaurant to javafx.fxml;
    exports com.restaurant;
    exports com.restaurant.util;
    opens com.restaurant.util to javafx.fxml;
    exports com.restaurant.controller;
    opens com.restaurant.controller to javafx.fxml;
    opens com.restaurant.administrator to javafx.fxml;
    exports com.restaurant.administrator to javafx.fxml;
    opens com.restaurant.customer to javafx.fxml;
    exports com.restaurant.customer to javafx.fxml;
}