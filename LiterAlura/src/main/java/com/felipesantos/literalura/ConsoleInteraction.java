package com.felipesantos.literalura;

import com.felipesantos.literalura.dto.AuthorDTO;
import com.felipesantos.literalura.dto.BookDTO;
import com.felipesantos.literalura.service.BookService;
import com.felipesantos.literalura.service.GutendexService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class ConsoleInteraction implements CommandLineRunner {

    private final BookService bookService;
    private final GutendexService gutendexService;

    public ConsoleInteraction(BookService bookService, GutendexService gutendexService) {
        this.bookService = bookService;
        this.gutendexService = gutendexService;
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("\nOpções:");
                System.out.println("1. Buscar livro por título na API Gutendex");
                System.out.println("2. Inserir livro no banco");
                System.out.println("3. Listar todos os livros (API e Banco)");
                System.out.println("4. Listar todos os autores do banco");
                System.out.println("5. Exibir quantidade de livros em um idioma");
                System.out.println("6. Sair");

                System.out.print("Escolha uma opção: ");
                String input = scanner.nextLine();

                int choice;
                try {
                    choice = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida! Por favor, insira um número.");
                    continue;
                }

                switch (choice) {
                    case 1 -> buscarLivroNaAPI(scanner);
                    case 2 -> inserirLivro(scanner);
                    case 3 -> listarLivros();
                    case 4 -> listarAutores();
                    case 5 -> contarLivrosPorIdioma(scanner);
                    case 6 -> {
                        System.out.println("Saindo...");
                        System.exit(0);
                    }
                    default -> System.out.println("Opção inválida! Tente novamente.");
                }
            } catch (Exception e) {
                System.out.println("Ocorreu um erro: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private void buscarLivroNaAPI(Scanner scanner) {
        System.out.print("Digite o título do livro: ");
        String title = scanner.nextLine();
        List<BookDTO> apiBooks = gutendexService.searchBooks(title);

        if (apiBooks.isEmpty()) {
            System.out.println("Nenhum livro encontrado.");
        } else {
            for (BookDTO book : apiBooks) {
                System.out.println(
                        "• Título: " + book.getTitle() +
                                ", Idioma: " + book.getLanguage() +
                                ", Autor: " + book.getAuthorName());
            }
        }
    }

    private void inserirLivro(Scanner scanner) {
        System.out.println("\nComo deseja inserir o livro?");
        System.out.println("1. Buscar na API");
        System.out.println("2. Inserir manualmente");

        System.out.print("Escolha uma opção: ");
        String input = scanner.nextLine();

        int subChoice;
        try {
            subChoice = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida! Operação cancelada.");
            return;
        }

        switch (subChoice) {
            case 1 -> salvarLivroDaAPI(scanner);
            case 2 -> inserirLivroManual(scanner);
            default -> System.out.println("Opção inválida! Operação cancelada.");
        }
    }

    private void salvarLivroDaAPI(Scanner scanner) {
        System.out.print("Digite o título do livro para buscar na API: ");
        String title = scanner.nextLine();
        List<BookDTO> apiBooks = gutendexService.searchBooks(title);

        if (apiBooks.isEmpty()) {
            System.out.println("Nenhum livro encontrado na API para o título: " + title);
        } else {
            System.out.println("Livros encontrados:");
            for (int i = 0; i < apiBooks.size(); i++) {
                System.out.println((i + 1) + ". " + apiBooks.get(i));
            }

            System.out.print("Digite o número do livro que deseja salvar no banco (ou 0 para cancelar): ");
            String input = scanner.nextLine();

            int option;
            try {
                option = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Operação cancelada.");
                return;
            }

            if (option > 0 && option <= apiBooks.size()) {
                BookDTO selectedBook = apiBooks.get(option - 1);
                bookService.saveBook(
                        selectedBook.getTitle(),
                        selectedBook.getLanguage(),
                        selectedBook.getAuthorName()
                );
                System.out.println("Livro salvo com sucesso!");
            } else {
                System.out.println("Operação cancelada.");
            }
        }
    }

    private void inserirLivroManual(Scanner scanner) {
        System.out.print("Digite o título do livro: ");
        String title = scanner.nextLine();
        System.out.print("Digite o idioma do livro: ");
        String language = scanner.nextLine();
        System.out.print("Digite o nome do autor: ");
        String authorName = scanner.nextLine();

        bookService.saveBook(title, language, authorName);
        System.out.println("Livro salvo com sucesso!");
    }

    private void listarLivros() {
        List<BookDTO> dbBooks = bookService.getAllBooksAsDTO();
        for (BookDTO book : dbBooks) {
            System.out.println(
                    "• Título: " + book.getTitle() +
                            ", Idioma: " + book.getLanguage() +
                            ", Autor: " + book.getAuthorName());
        }
    }

    private void listarAutores() {
        List<AuthorDTO> dbAuthors = bookService.getAllAuthorsASDTO();
        System.out.println("Autores no Banco:");
        for (AuthorDTO author : dbAuthors) {
            System.out.println("• " + author.getName());
        }
    }

    private void contarLivrosPorIdioma(Scanner scanner) {
        System.out.print("Digite o idioma: ");
        String language = scanner.nextLine();
        long count = bookService.countBooksByLanguage(language);
        System.out.println("Quantidade de livros no idioma " + language + ": " + count);
    }
}