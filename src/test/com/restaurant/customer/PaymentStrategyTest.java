package com.restaurant.customer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PaymentStrategyTest {

    @Test
    public void testCashPaymentStrategy() {
        IPaymentStrategy paymentStrategy = new CashIPaymentStrategy();
        double amount = 100.0;
        double expectedPayment = 100.0;
        assertEquals(expectedPayment, paymentStrategy.calculatePayment(amount), "Cash payment should return the same amount");
    }

    @Test
    public void testCreditCardPaymentStrategy() {
        IPaymentStrategy paymentStrategy = new CreditCardIPaymentStrategy();
        double amount = 100.0;
        double expectedPayment = 95.0;
        assertEquals(expectedPayment, paymentStrategy.calculatePayment(amount), "Credit card payment should return 95% of the amount");
    }

    @Test
    public void testMealVoucherPaymentStrategy() {
        IPaymentStrategy paymentStrategy = new MealVoucherStrategyI();
        double amount = 100.0;
        double expectedPayment = 80.0;
        assertEquals(expectedPayment, paymentStrategy.calculatePayment(amount), "Meal voucher payment should return 80% of the amount");
    }
}
