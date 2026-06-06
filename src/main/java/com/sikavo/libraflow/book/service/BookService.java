package com.sikavo.libraflow.book.service;

import com.sikavo.libraflow.book.Book;
import com.sikavo.libraflow.book.dto.BookRequest;
import com.sikavo.libraflow.book.dto.BookResponse;

import java.util.List;
import java.util.Optional;

public interface BookService {

    BookResponse createBook(BookRequest request);
    boolean deleteBookById(Long bookId);
    List<Book> searchBookByAuthorName(String author);
    Optional<Book> searchBookByIsbn(String isbn);

}
