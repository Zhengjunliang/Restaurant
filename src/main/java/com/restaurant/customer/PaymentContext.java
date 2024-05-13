package com.restaurant.customer;

class PaymentContext {
    private final PaymentStrategy paymentStrategy;

    public PaymentContext(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public double calculatePaymentAmount(double paymentAmount) {
        return paymentStrategy.calculatePayment(paymentAmount);
    }
}
