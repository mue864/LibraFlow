package com.sikavo.libraflow.book.controller;

import com.sikavo.libraflow.book.dto.BookRequest;
import com.sikavo.libraflow.book.dto.BookResponse;
import com.sikavo.libraflow.book.dto.UpdateBookRequest;
import com.sikavo.libraflow.book.dto.UpdateBookResponse;
import com.sikavo.libraflow.book.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@Tag(
        name = "Books",
        description = "Endpoints for managing library books"
)
@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @Operation(
            summary = "Create a new book",
            description = "Creates a new book in the library inventory."
    )
    @PostMapping
    public ResponseEntity<BookResponse> createBook(BookRequest request) {
        log.info("Starting create book process");
        BookResponse res = bookService.createBook(request);
        return ResponseEntity.ok(res);
    }

    @Operation(
            summary = "Delete an existing book",
            description = "Deletes an existing book from the library. Only admins can delete books"
    )
    @DeleteMapping
    public ResponseEntity<UpdateBookResponse> deleteBook(Long Id) {
        log.info("Starting delete method for book {}", Id);
        UpdateBookResponse response = bookService.softDeleteBook(Id);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Search book by author name",
            description = "Searches book by author name irregardless of case"
    )
    @GetMapping("/search/author/{authorName}")
    public ResponseEntity<List<BookResponse>> searchBookByAuthorName(@PathVariable String authorName) {
        log.info("Start searching for {}", authorName);
        List<BookResponse> books = bookService.searchBookByAuthorName(authorName);
        return ResponseEntity.ok(books);    }

    @Operation(
            summary = "Search by by ISBN",
            description = "Searches book by unique ISBN code"
    )
    @GetMapping("/search/isbn/{isbn}")
    public ResponseEntity<Optional<BookResponse>> searchBookByIsbn(@PathVariable String isbn) {
        log.info("Searching book by ISBN");

        Optional<BookResponse> books = bookService.searchBookByIsbn(isbn);
        return ResponseEntity.ok(books);
    }

    @Operation(
            summary = "Edit an existing Book",
            description = "Edits book"
    )
    @PatchMapping("/editBook/")
    public ResponseEntity<UpdateBookResponse> editBook(UpdateBookRequest updateBookRequest) {
        log.info("Starting edit book method");

        UpdateBookResponse bookUpdate = bookService.editBook(updateBookRequest);

        return ResponseEntity.ok(bookUpdate);
    }
}
