package com.restaurant.customer;

import com.restaurant.model.ConfigurationManager;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.Optional;

public class PaymentController {

    @FXML
    private Label PayAmount;
    private double totalAmount;

    @FXML
    public void initialize() {
        ConfigurationManager configurationManager = ConfigurationManager.getInstance();
        totalAmount = configurationManager.getPrice();
        PayAmount.setText(Double.toString(totalAmount));
    }

    @FXML
    public void CashPay(){
        PaymentContext cashPayment = new PaymentContext(new CashPaymentStrategy());
        double totalAmountTmp = cashPayment.calculatePaymentAmount(totalAmount);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Payment");
        alert.setHeaderText("You Payed with Cash and have " + totalAmountTmp + " to pay!");
        alert.setContentText("Press OK to pay");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Stage stage = (Stage) PayAmount.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    public void CardPay(){
        PaymentContext creditCardPayment  = new PaymentContext(new CreditCardPaymentStrategy());
        double totalAmountTmp = creditCardPayment .calculatePaymentAmount(totalAmount);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Payment");
        alert.setHeaderText("You Payed with Card with 5% discount and have " + totalAmountTmp + " to pay!");
        alert.setContentText("Press OK to pay");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Stage stage = (Stage) PayAmount.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    public void MealVoucherPay(){
        PaymentContext mealVoucherPayment  = new PaymentContext(new MealVoucherStrategy());
        double totalAmountTmp = mealVoucherPayment .calculatePaymentAmount(totalAmount);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Payment");
        alert.setHeaderText("You Payed with MealVoucher with 20% discount and have " + totalAmountTmp + " to pay!");
        alert.setContentText("Press OK to pay");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Stage stage = (Stage) PayAmount.getScene().getWindow();
            stage.close();
        }
    }
}
