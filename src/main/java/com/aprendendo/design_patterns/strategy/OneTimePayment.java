package com.aprendendo.design_patterns.strategy;

public class OneTimePayment implements PaymentStrategy {
    @Override
    public double pay(double amount) {
        return amount;
    }
}

