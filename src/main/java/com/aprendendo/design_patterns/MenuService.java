package com.aprendendo.design_patterns;

import com.aprendendo.design_patterns.factory.CourseFactory;
import com.aprendendo.design_patterns.model.*;
import com.aprendendo.design_patterns.observer.EmailNotifier;
import com.aprendendo.design_patterns.observer.LoggerNotifier;
import com.aprendendo.design_patterns.repository.CourseManager;
import com.aprendendo.design_patterns.strategy.*;

public class MenuService {
    private final CourseManager manager;
    private final java.util.Scanner scanner;

    public MenuService() {
        this.manager = CourseManager.getInstance();
        this.manager.addObserver(new EmailNotifier());
        this.manager.addObserver(new LoggerNotifier());
        this.scanner = new java.util.Scanner(System.in);
    }

    public void start() {
        boolean executando = true;
        while (executando) {
            printMenu();
            int opcao = readInt("Digite sua opção: ");
            System.out.println("────────────────────────────────────────────");
            switch (opcao) {
                case 1:
                    cadastrarCurso();
                    break;
                case 2:
                    listarCursos();
                    break;
                case 3:
                    matricularAluno();
                    break;
                case 4:
                    listarMatriculas();
                    break;
                case 5:
                    realizarPagamento();
                    break;
                case 0:
                    System.out.println("\nObrigado por usar a Plataforma de Cursos! Até logo.");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
            System.out.println("────────────────────────────────────────────");
        }
    }

    private void printMenu() {
        System.out.println("\n============================================");
        System.out.println("        PLATAFORMA DE CURSOS - MENU");
        System.out.println("============================================");
        System.out.println(" 1. Cadastrar novo curso");
        System.out.println(" 2. Listar cursos");
        System.out.println(" 3. Matricular aluno");
        System.out.println(" 4. Listar matrículas");
        System.out.println(" 5. Realizar pagamento");
        System.out.println(" 0. Sair");
    }

    private int readInt(String prompt) {
        System.out.print(prompt);
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            return -1;
        }
    }

    private double readDouble() {
        try {
            return Double.parseDouble(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Valor inválido! Usando 0.0");
            return 0.0;
        }
    }

    private void cadastrarCurso() {
        System.out.print("Tipo do curso (online/presencial/workshop): ");
        String tipo = scanner.nextLine().trim().toLowerCase();
        System.out.print("Nome do curso: ");
        String nome = scanner.nextLine();
        System.out.print("Preço (R$): ");
        double preco = readDouble();
        try {
            Course novoCurso = CourseFactory.create(tipo, nome, preco);
            manager.addCourse(novoCurso);
            System.out.println("Curso cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar curso: " + e.getMessage());
        }
    }

    private void listarCursos() {
        System.out.println("\n--- CURSOS CADASTRADOS ---");
        if (manager.listCourses().isEmpty()) {
            System.out.println("Nenhum curso cadastrado.");
            return;
        }
        for (Course c : manager.listCourses()) {
            System.out.printf("• %-30s | R$ %7.2f\n", c.getName(), c.getPrice());
        }
    }

    private void matricularAluno() {
        System.out.print("Nome do aluno: ");
        String aluno = scanner.nextLine();
        System.out.print("E-mail do aluno: ");
        String email = scanner.nextLine();
        System.out.print("Nome do curso: ");
        String cursoMat = scanner.nextLine();
        Course cursoEscolhido = manager.listCourses().stream()
                .filter(c -> c.getName().equalsIgnoreCase(cursoMat))
                .findFirst().orElse(null);
        if (cursoEscolhido == null) {
            System.out.println("Curso não encontrado!");
            return;
        }
        System.out.print("Forma de pagamento (gratuito/único/assinatura): ");
        String forma = scanner.nextLine().trim().toLowerCase();
        PaymentStrategy pagamento = getPaymentStrategyPt(forma);
        Enrollment novaMatricula = new Enrollment(cursoEscolhido, new Student(aluno, email), pagamento);
        manager.enrollStudent(novaMatricula);
        System.out.println("Aluno matriculado com sucesso!");
    }

    private PaymentStrategy getPaymentStrategyPt(String forma) {
        if (forma.equals("gratuito")) return new FreePayment();
        if (forma.equals("unico") || forma.equals("unico")) return new OneTimePayment();
        if (forma.equals("assinatura")) return new SubscriptionPayment();
        System.out.println("Forma de pagamento inválida! Usando 'gratuito' por padrão.");
        return new FreePayment();
    }

    private void listarMatriculas() {
        System.out.println("\n--- MATRÍCULAS ---");
        if (manager.listEnrollments().isEmpty()) {
            System.out.println("Nenhuma matrícula encontrada.");
            return;
        }
        for (Enrollment e : manager.listEnrollments()) {
            System.out.printf("%s em %-30s | Status: %s\n",
                    e.getStudent().getName(), e.getCourse().getName(), e.getStatus());
        }
    }

    private void realizarPagamento() {
        System.out.print("Nome do aluno: ");
        String alunoPag = scanner.nextLine();
        System.out.print("Nome do curso: ");
        String cursoPag = scanner.nextLine();
        Enrollment mat = manager.listEnrollments().stream()
                .filter(e -> e.getStudent().getName().equalsIgnoreCase(alunoPag)
                        && e.getCourse().getName().equalsIgnoreCase(cursoPag))
                .findFirst().orElse(null);
        if (mat == null) {
            System.out.println("Matrícula não encontrada!");
            return;
        }
        double valor = mat.pay();
        System.out.printf("Pagamento realizado: R$ %.2f | Status: %s\n", valor, mat.getStatus());
    }
}
