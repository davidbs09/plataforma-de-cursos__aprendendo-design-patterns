package com.aprendendo.design_patterns.repository;

import com.aprendendo.design_patterns.model.Course;
import com.aprendendo.design_patterns.model.Enrollment;
import com.aprendendo.design_patterns.observer.Observer;
import java.util.*;

public class CourseManager {
    private static CourseManager instance;
    private List<Course> courses = new ArrayList<>();
    private List<Enrollment> enrollments = new ArrayList<>();
    private List<Observer> observers = new ArrayList<>();

    private CourseManager() {}

    public static CourseManager getInstance() {
        if (instance == null) {
            instance = new CourseManager();
        }
        return instance;
    }

    public void addCourse(Course course) {
        courses.add(course);
        notifyObservers(course);
    }

    public void enrollStudent(Enrollment enrollment) {
        enrollments.add(enrollment);
    }

    public List<Course> listCourses() {
        return Collections.unmodifiableList(courses);
    }

    public List<Enrollment> listEnrollments() {
        return Collections.unmodifiableList(enrollments);
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    private void notifyObservers(Course course) {
        for (Observer observer : observers) {
            observer.update(course);
        }
    }
}
