package com.sikavo.libraflow.user.model;

import com.sikavo.libraflow.book.Book;
import com.sikavo.libraflow.library.model.Library;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userId;

    @Column(name = "library_code")
    private UUID libraryCode;

    @Column(name = "user_name", length = 100, unique = true)
    private String userName;

    @Column(name = "email", length = 80, unique = true)
    private String email;

    @Column
    private String password;


    @Column
    private boolean isUserActive = false;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    private void onCreate () {this.createdAt = LocalDateTime.now();};

    @PreUpdate
    private void onUpdate () {this.updatedAt = LocalDateTime.now(); };


}
