package com.sikavo.libraflow.library.controller;

import com.sikavo.libraflow.library.dto.LibraryRequest;
import com.sikavo.libraflow.library.dto.LibraryResponse;
import com.sikavo.libraflow.library.model.Library;
import com.sikavo.libraflow.library.repository.LibraryRepository;
import com.sikavo.libraflow.library.service.LibraryService;
import com.sikavo.libraflow.library.service.impl.LibraryServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Tag(
        name = "Library",
        description = "Endpoints for managing libraries"
)
@RestController
@RequestMapping("/api/v1/library")
public class LibraryController {
    private final LibraryServiceImpl libraryService;

    @Autowired
    public LibraryController(LibraryServiceImpl libraryService) {
        this.libraryService = libraryService;
    }

    @Operation(
            summary = "Create a new library",
            description = "Create a new library asset"
    )
    @PostMapping("/create")
    public ResponseEntity<LibraryResponse> createLibrary(LibraryRequest request) {
        LibraryResponse library = libraryService.createLibrary(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(library);
    }

    @Operation(
            summary = "Delete a library",
            description = "Delete library from the database"
    )
    @DeleteMapping("/delete/{libraryId}")
    public ResponseEntity<Void> deleteLibrary(@PathVariable UUID libraryId) {
        libraryService.deleteLibrary(libraryId);

        return ResponseEntity.noContent().build();
    };

    @Operation(
            summary = "Search library from location",
            description = "Find a library based on the provided search location or string"
    )
    @GetMapping("/search/library/{libraryLocation}")
    public Optional<Library> searchLibrary(@RequestParam String libraryLocation) {
        return libraryService.findLibraryByLocation(libraryLocation);
    };

    @Operation(
            summary = "Find library by library code",
            description = "Search for library using library code (uuid)"
    )
    @GetMapping("/search/libraryCode/{libraryId}")
    public Optional<Library> searchLibraryByCode(@RequestParam UUID libraryCode) {
        return libraryService.findLibraryByCode(libraryCode);
    };

}
