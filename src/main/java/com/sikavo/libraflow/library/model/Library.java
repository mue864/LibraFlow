package com.sikavo.libraflow.library.model;

import com.sikavo.libraflow.book.Book;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@Entity
public class Library {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID libraryCode;

    @Column(name = "library_name", length = 100, nullable = false)
    private String libraryName;

    @Column(name = "library_books")
    private List<Book> bookList;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    private void onCreate () {this.createdAt = LocalDateTime.now(); };

    @PreUpdate void onUpdate () {this.updatedAt = LocalDateTime.now(); };
}
