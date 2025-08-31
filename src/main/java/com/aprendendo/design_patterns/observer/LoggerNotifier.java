package com.aprendendo.design_patterns.observer;

import com.aprendendo.design_patterns.model.Course;

public class LoggerNotifier implements Observer {
    @Override
    public void update(Course c) {
        System.out.println("[LOG] Curso criado: " + c.getName());
    }
}
