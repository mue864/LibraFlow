package com.sikavo.libraflow.book.mapper;

import com.sikavo.libraflow.book.Book;
import com.sikavo.libraflow.book.dto.BookRequest;
import com.sikavo.libraflow.book.dto.BookResponse;
import com.sikavo.libraflow.library.dto.LibraryResponse;
import com.sikavo.libraflow.library.model.Library;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BookMapper {

    public String normalizeStrings(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        return value.trim();
    }
    public Book toEntity (BookRequest request) {
        Book book = new Book();
        Library library = new Library();
        book.setISBN(normalizeStrings(request.ISBN()));
        log.info("Normalized ISBN: {}", normalizeStrings(request.ISBN()));
        book.setTitle(normalizeStrings(request.title()));
        book.setEdition(normalizeStrings(request.edition()));
        book.setAuthor(normalizeStrings(request.author()));
        book.setAvailableCopies(request.totalCopies());
        book.setTotalCopies(request.totalCopies());
//        book.setLibrary(library);

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
