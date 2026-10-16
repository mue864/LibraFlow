package com.sikavo.libraflow.library.mapper;

import com.sikavo.libraflow.library.dto.LibraryRequest;
import com.sikavo.libraflow.library.dto.LibraryResponse;
import com.sikavo.libraflow.library.model.Library;
import org.springframework.stereotype.Component;

@Component
public class LibraryMapper {

    private String removeBlank(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }

        return value.trim();
    }

    public Library toEntity(LibraryRequest request) {
        Library library = new Library();
        library.setLibraryName(removeBlank(request.libraryName()));
        library.setLibraryLocation(removeBlank(request.libraryLocation()));

        return library;
    }

    public LibraryResponse toResponse (Library library) {
        return new LibraryResponse(
                library.getLibraryCode(),
                library.getLibraryName(),
                library.getLibraryLocation(),
                library.getCreatedAt()
        );
    }
}
