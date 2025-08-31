package com.aprendendo.design_patterns;

import com.aprendendo.design_patterns.factory.CourseFactory;
import com.aprendendo.design_patterns.model.*;
import com.aprendendo.design_patterns.observer.*;
import com.aprendendo.design_patterns.repository.CourseManager;
import com.aprendendo.design_patterns.strategy.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DesignPatternsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DesignPatternsApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo() {
        return args -> {
            CourseManager manager = CourseManager.getInstance();

            // Observadores
            manager.addObserver(new EmailNotifier());
            manager.addObserver(new LoggerNotifier());

            // Criação de cursos
            Course c1 = CourseFactory.create("online", "Java Básico", 0.0);
            Course c2 = CourseFactory.create("presential", "Algoritmos Avançados", 500.0);
            Course c3 = CourseFactory.create("workshop", "Spring Boot Hands-on", 200.0);
            manager.addCourse(c1);
            manager.addCourse(c2);
            manager.addCourse(c3);

            // Alunos
            Student s1 = new Student("Ana", "ana@email.com");
            Student s2 = new Student("Bruno", "bruno@email.com");

            // Matrículas
            Enrollment e1 = new Enrollment(c1, s1, new FreePayment());
            Enrollment e2 = new Enrollment(c2, s2, new OneTimePayment());
            Enrollment e3 = new Enrollment(c3, s1, new SubscriptionPayment());
            manager.enrollStudent(e1);
            manager.enrollStudent(e2);
            manager.enrollStudent(e3);

            // Pagamentos
            System.out.println("Pagamento e1: R$" + e1.pay() + " | Status: " + e1.getStatus());
            System.out.println("Pagamento e2: R$" + e2.pay() + " | Status: " + e2.getStatus());
            System.out.println("Pagamento e3: R$" + e3.pay() + " | Status: " + e3.getStatus());

            // Listagem
            System.out.println("\nCursos cadastrados:");
            for (Course c : manager.listCourses()) {
                System.out.println("- " + c.getName() + " (R$" + c.getPrice() + ")");
            }
            System.out.println("\nMatrículas:");
            for (Enrollment e : manager.listEnrollments()) {
                System.out.println(e.getStudent().getName() + " em " + e.getCourse().getName() + " | Status: " + e.getStatus());
            }
        };
    }
}
