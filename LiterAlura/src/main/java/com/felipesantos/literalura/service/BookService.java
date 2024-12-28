package com.felipesantos.literalura.service;

import com.felipesantos.literalura.dto.AuthorDTO;
import com.felipesantos.literalura.dto.BookDTO;
import com.felipesantos.literalura.model.Author;
import com.felipesantos.literalura.model.Book;
import com.felipesantos.literalura.repository.AuthorRepository;
import com.felipesantos.literalura.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public Book saveBook(String title, String language, String authorName) {
        Author author = authorRepository.findByName(authorName)
                .orElseGet(() -> {
                    Author newAuthor = new Author();
                    newAuthor.setName(authorName);
                    return authorRepository.save(newAuthor);
                });

        Book book = new Book();
        book.setTitle(title);
        book.setLanguage(language);
        book.setAuthor(author);
        return bookRepository.save(book);
    }

    public List<BookDTO> getAllBooksAsDTO() {
        return bookRepository.findAll().stream()
                .map(book -> new BookDTO(
                        book.getId(),
                        book.getTitle(),
                        book.getLanguage(),
                        book.getAuthor().getName(),
                        "Database"
                ))
                .collect(Collectors.toList());
    }

    public List<AuthorDTO> getAllAuthorsASDTO() {
        return authorRepository.findAll().stream().map(author -> new AuthorDTO(author.getId(), author.getName()))
                .collect(Collectors.toList());
    }

    public long countBooksByLanguage(String language) {
        return bookRepository.countByLanguage(language);
    }
}
