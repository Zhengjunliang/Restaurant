package com.restaurant.customer;

class PaymentContext {
    private final IPaymentStrategy IPaymentStrategy;

    public PaymentContext(IPaymentStrategy IPaymentStrategy) {
        this.IPaymentStrategy = IPaymentStrategy;
    }

    public double calculatePaymentAmount(double paymentAmount) {
        return IPaymentStrategy.calculatePayment(paymentAmount);
    }
}
