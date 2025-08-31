package com.aprendendo.design_patterns.factory;

import com.aprendendo.design_patterns.model.Course;
import com.aprendendo.design_patterns.model.OnlineCourse;
import com.aprendendo.design_patterns.model.PresentialCourse;
import com.aprendendo.design_patterns.model.Workshop;

public class CourseFactory {
    public static Course create(String type, String name, double price) {
        switch (type.toLowerCase()) {
            case "online":
                return new OnlineCourse(name, price);
            case "presential":
            case "presencial":
                return new PresentialCourse(name, price);
            case "workshop":
                return new Workshop(name, price);
            default:
                throw new IllegalArgumentException("Tipo de curso inválido: " + type);
        }
    }
}
