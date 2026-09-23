package com.sikavo.libraflow.book.service.impl;

import com.sikavo.libraflow.book.Book;
import com.sikavo.libraflow.book.dto.BookRequest;
import com.sikavo.libraflow.book.dto.BookResponse;
import com.sikavo.libraflow.book.mapper.BookMapper;
import com.sikavo.libraflow.book.repository.BookRepository;
import com.sikavo.libraflow.book.service.BookService;
import com.sikavo.libraflow.common.exception.BookAlreadyExistsException;
import com.sikavo.libraflow.common.exception.BookNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookServiceImpl(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    public String normalizeStringValues(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        return value.trim();
    }

    @Override
    public BookResponse createBook(BookRequest request) {
        if (bookRepository.existsByISBN(normalizeStringValues(request.ISBN()))) {
            throw new BookAlreadyExistsException("Book already exists");
        }
        Book book = bookMapper.toEntity(request);
        Book savedbook = bookRepository.save(book);

        return bookMapper.toResponse(savedbook);
    }

    @Override
    public void deleteBookById(Long bookId) {
        if (!bookRepository.existsById(bookId)) {
            throw new BookNotFoundException("Book does not exists");
        }
        bookRepository.deleteBookByBookId(bookId);
    }

    @Override
    public List<BookResponse> searchBookByAuthorName(String author) {
        author = normalizeStringValues(author);
        if (author == null || author.isBlank()) {
            return List.of();
        }
        return bookRepository.findBookByAuthorIgnoreCase(author);
    }

    @Override
    public Optional<BookResponse> searchBookByIsbn(String isbn) {
        isbn = normalizeStringValues(isbn);
        if (isbn == null || isbn.isBlank()) return Optional.empty();
        return bookRepository.findBookByISBNIgnoreCase(isbn);
    }
}
