package com.sikavo.libraflow.book.dto;

import java.time.LocalDateTime;

public record UpdateBookResponse (
        Long BookId,
        String author,
        String title,
        String ISBN,
        String edition,
        int totalCopies,
        boolean isDeleted,
        LocalDateTime timeDeleted
) {}
