package com.sikavo.libraflow.book.service;

import com.sikavo.libraflow.book.Book;
import com.sikavo.libraflow.book.dto.BookRequest;
import com.sikavo.libraflow.book.dto.BookResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface BookService {

    BookResponse createBook(BookRequest request);
    void deleteBookById(Long bookId);
    List<BookResponse> searchBookByAuthorName(String author);
    Optional<BookResponse> searchBookByIsbn(String isbn);

}
