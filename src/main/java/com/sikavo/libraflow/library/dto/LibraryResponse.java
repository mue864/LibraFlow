package com.sikavo.libraflow.library.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record LibraryResponse(
        UUID libraryCode,
        String libraryName,
        String libraryLocation,
        LocalDateTime createdAt
) {
}
