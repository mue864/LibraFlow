package com.sikavo.libraflow.library.service;

import com.sikavo.libraflow.library.dto.LibraryRequest;
import com.sikavo.libraflow.library.dto.LibraryResponse;
import com.sikavo.libraflow.library.model.Library;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

public interface LibraryService {
    LibraryResponse createLibrary(LibraryRequest request);
    void deleteLibrary(UUID libraryCode);
    Optional<Library> findLibraryByLocation(String libraryLocation);
    Optional<Library> findLibraryByCode(UUID libraryCode);

}
