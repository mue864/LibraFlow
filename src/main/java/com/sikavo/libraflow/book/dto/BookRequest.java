package com.sikavo.libraflow.book.dto;

import jakarta.validation.constraints.NotBlank;

public record BookRequest(
      String ISBN,
      String edition,
      @NotBlank String author,
      @NotBlank String title,
      int totalCopies
) {}
