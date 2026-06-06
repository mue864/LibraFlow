package com.sikavo.libraflow.book.mapper;

import com.sikavo.libraflow.book.Book;
import com.sikavo.libraflow.book.dto.BookRequest;
import com.sikavo.libraflow.book.dto.BookResponse;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public String normalizeStrings(String value) {
        if (value == null || !value.isBlank()) {
            return null;
        }
        return value.trim();
    }
    public Book toEntity (BookRequest request) {
        Book book = new Book();
        book.setISBN(normalizeStrings(request.ISBN()));
        book.setTitle(normalizeStrings(request.title()));
        book.setEdition(normalizeStrings(request.edition()));
        book.setAuthor(normalizeStrings(request.author()));
        book.setAvailableCopies(request.totalCopies());
        book.setTotalCopies(request.totalCopies());

        return book;
    }

    public BookResponse toResponse(Book book) {
        return new BookResponse(
                book.getBookId(),
                book.getAuthor(),
                book.getTitle(),
                book.getISBN(),
                book.getEdition(),
                book.getTotalCopies(),
                book.getAvailableCopies()
        );
    }

}
