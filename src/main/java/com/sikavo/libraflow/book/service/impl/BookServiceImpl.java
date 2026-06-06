package com.sikavo.libraflow.book.service.impl;

import com.sikavo.libraflow.book.Book;
import com.sikavo.libraflow.book.dto.BookRequest;
import com.sikavo.libraflow.book.dto.BookResponse;
import com.sikavo.libraflow.book.mapper.BookMapper;
import com.sikavo.libraflow.book.repository.BookRepository;
import com.sikavo.libraflow.book.service.BookService;
import com.sikavo.libraflow.common.exception.BookAlreadyExistsException;
import com.sikavo.libraflow.common.exception.BookNotFoundException;

import java.util.List;
import java.util.Optional;

public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private BookMapper bookMapper;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public String normalizeStringValues(String value) {
        if (value == null || !value.isBlank()) {
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
    public boolean deleteBookById(Long bookId) {
        if (!bookRepository.existsById(bookId)) {
            throw new BookNotFoundException("Book does not exists");
        }
        return bookRepository.deleteBookByBookId(bookId);
    }

    @Override
    public List<Book> searchBookByAuthorName(String author) {
        author = normalizeStringValues(author);
        if (author == null || author.isBlank()) {
            return List.of();
        }
        return bookRepository.findBookByAuthorIgnoreCase(author);
    }

    @Override
    public Optional<Book> searchBookByIsbn(String isbn) {
        isbn = normalizeStringValues(isbn);
        if (isbn == null || isbn.isBlank()) return Optional.empty();
        return bookRepository.findBookByISBNIgnoreCase(isbn);
    }
}
