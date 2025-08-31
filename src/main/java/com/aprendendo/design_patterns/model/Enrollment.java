package com.aprendendo.design_patterns.model;

import com.aprendendo.design_patterns.strategy.PaymentStrategy;

public class Enrollment {
    private Course course;
    private Student student;
    private String status;
    private PaymentStrategy paymentStrategy;

    public Enrollment(Course course, Student student, PaymentStrategy paymentStrategy) {
        this.course = course;
        this.student = student;
        this.paymentStrategy = paymentStrategy;
        this.status = "PENDENTE";
    }

    public Course getCourse() {
        return course;
    }

    public Student getStudent() {
        return student;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double pay() {
        double valorPago = paymentStrategy.pay(course.getPrice());
        if (valorPago == 0.0 || valorPago == course.getPrice()) {
            this.status = "PAGO";
        }
        return valorPago;
    }
}
