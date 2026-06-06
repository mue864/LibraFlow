package com.sikavo.libraflow.book;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    @Column(unique = true)
    private String ISBN;

    @Column(length = 100)
    private String edition;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    private int totalCopies;
    private int availableCopies;

    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    @PrePersist
    public void onCreate () {
        this.createDate = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate () {
        this.updateDate = LocalDateTime.now();
    }

}
