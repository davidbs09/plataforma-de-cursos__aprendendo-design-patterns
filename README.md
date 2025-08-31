# Plataforma de Cursos - Design Patterns em Java

Este projeto foi desenvolvido como desafio do bootcamp para demonstrar, na prática, a aplicação de diversos padrões de projeto (Design Patterns) em Java, seguindo também princípios de Clean Code.

## Objetivo

Criar uma plataforma de cursos simples, mas robusta, que utiliza padrões de projeto clássicos para resolver problemas reais de organização, flexibilidade e manutenção do código. O sistema permite:
- Cadastro de cursos (online, presencial e workshop)
- Matrícula de alunos
- Pagamento de matrículas
- Notificações automáticas
- Listagem de cursos e matrículas
- Interação via menu no console

## Padrões de Projeto Utilizados

- **Factory Method**: Criação de diferentes tipos de cursos (online, presencial, workshop) de forma flexível e desacoplada.
- **Strategy**: Diferentes formas de pagamento (gratuito, único, assinatura) para matrículas, permitindo fácil extensão e manutenção.
- **Observer**: Notificação automática de eventos importantes (como novo curso cadastrado) para múltiplos canais (e-mail, log), promovendo baixo acoplamento.
- **Singleton**: Gerenciamento centralizado dos cursos e matrículas, garantindo uma única instância do gerenciador em toda a aplicação.

## Práticas de Clean Code

- Separação clara de responsabilidades entre as classes
- Nomes de métodos e variáveis autoexplicativos
- Interação com o usuário 100% em português, tornando o sistema acessível
- Código modular, reutilizável e fácil de manter
- Menu interativo limpo e intuitivo

## Estrutura do Projeto

```
src/
  main/
    java/
      com/aprendendo/design_patterns/
        DesignPatternsApplication.java  # Classe principal (Spring Boot)
        MenuService.java               # Menu interativo e interface com o usuário
        factory/                       # Factory Method para cursos
        model/                         # Entidades: Curso, Aluno, Matrícula, etc.
        observer/                      # Observers para notificações
        repository/                    # Singleton para gerenciamento
        strategy/                      # Estratégias de pagamento
  test/
    java/
      com/aprendendo/design_patterns/  # Testes automatizados
```

## Como Executar

1. Clone o repositório
2. Execute o projeto com Maven ou via IDE (Java 21+)
3. Siga o menu no console para interagir com a plataforma

## Considerações Finais

Este projeto demonstra, de forma didática e prática, como aplicar os principais padrões de projeto do universo Java, aliados a boas práticas de código limpo. Sinta-se à vontade para explorar, modificar e evoluir a solução!

---

Desafio realizado para o bootcamp, com foco em aprendizado e excelência técnica.
