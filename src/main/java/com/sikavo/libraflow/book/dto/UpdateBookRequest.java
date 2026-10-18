package com.sikavo.libraflow.book.dto;

public record UpdateBookRequest (
        Long bookId,
        String ISBN,
        String edition,
        String author,
        String title,
        int totalCopies,
        boolean delete
) {}
