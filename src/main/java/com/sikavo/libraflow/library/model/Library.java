package com.sikavo.libraflow.library.model;

import com.sikavo.libraflow.book.Book;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Library {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID libraryCode;

    @Column(name = "library_name", length = 100, nullable = false)
    private String libraryName;

   @OneToMany(mappedBy = "library")
    private Set<Book> books = new HashSet<>();

    @Column(name = "library_location", length = 200, nullable = false)
    private String libraryLocation;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private UUID returnLibraryCode() {
        return this.libraryCode;
    }

    @PrePersist
    private void onCreate () {this.createdAt = LocalDateTime.now(); };

    @PreUpdate
    private void onUpdate () {this.updatedAt = LocalDateTime.now(); };
}
