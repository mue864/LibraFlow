package com.sikavo.libraflow.user.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserResponse(
        UUID userId,
        LocalDateTime createdAt,
        String userName,
        String email,
        UUID LibraryCode,
        boolean isActive

) { }
