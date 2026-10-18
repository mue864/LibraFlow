package com.sikavo.libraflow.book.service;

import com.sikavo.libraflow.book.Book;
import com.sikavo.libraflow.book.dto.BookRequest;
import com.sikavo.libraflow.book.dto.BookResponse;
import com.sikavo.libraflow.book.dto.UpdateBookRequest;
import com.sikavo.libraflow.book.dto.UpdateBookResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface BookService {

    BookResponse createBook(BookRequest request);
    UpdateBookResponse softDeleteBook(Long bookId);
    List<BookResponse> searchBookByAuthorName(String author);
    Optional<BookResponse> searchBookByIsbn(String isbn);
    UpdateBookResponse editBook(UpdateBookRequest request);
}
