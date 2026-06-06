package com.sikavo.libraflow.book.dto;

public record BookResponse (
         Long bookId,
         String author,
         String title,
         String ISBN,
         String edition,
         int totalCopies,
         int availableCopies
){}
