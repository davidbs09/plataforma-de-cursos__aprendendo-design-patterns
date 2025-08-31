package com.aprendendo.design_patterns.strategy;

public class SubscriptionPayment implements PaymentStrategy {
    @Override
    public double pay(double amount) {
        // Exemplo: retorna valor mensal (poderia ser customizado)
        return amount / 12;
    }
}

