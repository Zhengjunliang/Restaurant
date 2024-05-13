package com.restaurant.customer;

class CashPaymentStrategy implements PaymentStrategy {
    @Override
    public double calculatePayment(double amount) {
        return amount;
    }
}

class CreditCardPaymentStrategy implements PaymentStrategy {
    @Override
    public double calculatePayment(double amount) {
        return amount * 0.95;
    }
}

class MealVoucherStrategy implements PaymentStrategy {
    @Override
    public double calculatePayment(double amount) {
        return amount * 0.80;
    }
}