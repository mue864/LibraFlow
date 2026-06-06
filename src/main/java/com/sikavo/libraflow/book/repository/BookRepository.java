package com.sikavo.libraflow.book.repository;


import com.sikavo.libraflow.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findBookByISBNIgnoreCase(String isbn);
    Optional<Book> findBookByBookId(Long bookId);
    List<Book> findBookByAuthorIgnoreCase(String author);
    List<Book> findBookByTitleIgnoreCase(String title);
    boolean deleteBookByBookId(Long bookId);
    boolean existsByISBN(String isbn);

}
