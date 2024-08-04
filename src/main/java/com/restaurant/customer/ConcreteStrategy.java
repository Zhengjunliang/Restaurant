package com.restaurant.customer;

class CashIPaymentStrategy implements IPaymentStrategy {
    @Override
    public double calculatePayment(double amount) {
        return amount;
    }
}

class CreditCardIPaymentStrategy implements IPaymentStrategy {
    @Override
    public double calculatePayment(double amount) {
        return amount * 0.95;
    }
}

class MealVoucherStrategyI implements IPaymentStrategy {
    @Override
    public double calculatePayment(double amount) {
        return amount * 0.80;
    }
}