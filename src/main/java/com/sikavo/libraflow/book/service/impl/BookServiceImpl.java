package com.sikavo.libraflow.book.service.impl;

import com.sikavo.libraflow.book.Book;
import com.sikavo.libraflow.book.dto.BookRequest;
import com.sikavo.libraflow.book.dto.BookResponse;
import com.sikavo.libraflow.book.dto.UpdateBookRequest;
import com.sikavo.libraflow.book.dto.UpdateBookResponse;
import com.sikavo.libraflow.book.mapper.BookMapper;
import com.sikavo.libraflow.book.repository.BookRepository;
import com.sikavo.libraflow.book.service.BookService;
import com.sikavo.libraflow.common.exception.BookAlreadyExistsException;
import com.sikavo.libraflow.common.exception.BookNotFoundException;
import jakarta.transaction.Transactional;
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

    public boolean checkIfStringNull(String value) {
        return value != null && !value.isBlank();
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
    public UpdateBookResponse softDeleteBook(Long bookId) {
        if (!bookRepository.existsById(bookId)) {
            throw new BookNotFoundException("Book does not exist");
        }
//        we are not hard deleting, we soft delete for records
        Book book = bookRepository.findBookByBookId(bookId);
        book.setDeleted(true);
        bookRepository.save(book);

        return new UpdateBookResponse(
                book.getBookId(),
                book.getAuthor(),
                book.getTitle(),
                book.getISBN(),
                book.getEdition(),
                book.getTotalCopies(),
                book.isDeleted(),
                book.getUpdateDate()
        );
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

    @Transactional
    @Override
    public UpdateBookResponse editBook(UpdateBookRequest request) {
        Book book = bookRepository.findBookByBookId(request.bookId());

        if (!bookRepository.existsById(request.bookId())) {
            throw new BookNotFoundException("Book not found");
        }

        if (checkIfStringNull(request.title())) {
            book.setTitle(request.title());
        }

        if (checkIfStringNull(request.edition())) {
            book.setEdition(request.edition());
        }

        if (checkIfStringNull(request.author())) {
            book.setAuthor(request.author());
        }

        bookRepository.save(book);

        return new UpdateBookResponse(
                book.getBookId(),
                book.getAuthor(),
                book.getTitle(),
                book.getISBN(),
                book.getEdition(),
                book.getTotalCopies(),
                book.isDeleted(),
                book.getUpdateDate()
        );
    }

    @Override
    public List<Book> findAllUndeletedBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> findAllDeletedBooks() {
        return bookRepository.findByDeletedFalse();
    };

    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAllBooks();
    }
}
