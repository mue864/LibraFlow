package com.sikavo.libraflow.user.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record UserRequest(
        @NotBlank String userName,
        @NotBlank String email,
        UUID libraryCode
) { }
