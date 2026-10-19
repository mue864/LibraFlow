package com.sikavo.libraflow.book.repository;


import com.sikavo.libraflow.book.Book;
import com.sikavo.libraflow.book.dto.BookResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<BookResponse> findBookByISBNIgnoreCase(String isbn);

//    Lists only books not soft deleted
    @Override
    @Query("SELECT b FROM Book b WHERE b.deleted = false ")
    List<Book> findAll();
    List<Book> findByDeletedFalse();

    @Query("SELECT b FROM Book b WHERE b.deleted = false AND b.deleted = true")
    List<Book> findAllBooks();
//    @Query("select * from book where a.name ")
    List<BookResponse> findBookByAuthorIgnoreCase(String author);
    List<Book> findBookByTitleIgnoreCase(String title);
    void deleteBookByBookId(Long bookId);
    boolean existsByISBN(String isbn);

    Book findBookByBookId(Long bookId);

}
