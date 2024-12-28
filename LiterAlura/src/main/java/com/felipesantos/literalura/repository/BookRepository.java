package com.felipesantos.literalura.repository;

import com.felipesantos.literalura.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    long countByLanguage(String language);
}
