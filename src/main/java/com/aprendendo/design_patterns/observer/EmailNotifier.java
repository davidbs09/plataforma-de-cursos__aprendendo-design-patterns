package com.aprendendo.design_patterns.observer;

import com.aprendendo.design_patterns.model.Course;

public class EmailNotifier implements Observer {
    @Override
    public void update(Course c) {
        System.out.println("[E-MAIL] Novo curso disponível: " + c.getName());
    }
}
