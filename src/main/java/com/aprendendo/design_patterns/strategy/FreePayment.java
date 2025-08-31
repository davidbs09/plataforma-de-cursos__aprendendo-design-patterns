package com.aprendendo.design_patterns.strategy;

public class FreePayment implements PaymentStrategy {
    @Override
    public double pay(double amount) {
        return 0.0;
    }
}

