package com.restaurant.customer;

interface PaymentStrategy {
    double calculatePayment(double amount);
}