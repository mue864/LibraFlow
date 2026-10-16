package com.sikavo.libraflow.library.service.impl;

import com.sikavo.libraflow.common.exception.LibraryNotFoundException;
import com.sikavo.libraflow.library.dto.LibraryRequest;
import com.sikavo.libraflow.library.dto.LibraryResponse;
import com.sikavo.libraflow.library.mapper.LibraryMapper;
import com.sikavo.libraflow.library.model.Library;
import com.sikavo.libraflow.library.repository.LibraryRepository;
import com.sikavo.libraflow.library.service.LibraryService;

import java.util.Optional;
import java.util.UUID;

import com.sikavo.libraflow.utils.CleanStrings;
import org.springframework.stereotype.Service;

@Service
public class LibraryServiceImpl implements LibraryService {

    private final LibraryRepository libraryRepository;
    private final LibraryMapper libraryMapper;

    public LibraryServiceImpl(LibraryRepository libraryRepository, LibraryMapper libraryMapper) {
        this.libraryMapper = libraryMapper;
        this.libraryRepository = libraryRepository;
    }

    @Override
    public LibraryResponse createLibrary(LibraryRequest request) {
        Library library = libraryMapper.toEntity(request);
        Library savedLibrary = libraryRepository.save(library);

        return libraryMapper.toResponse(savedLibrary);
    }

    @Override
    public void deleteLibrary(UUID libraryCode) {
         libraryRepository.findLibraryByLibraryCode(libraryCode)
                .orElseThrow(() -> new LibraryNotFoundException("Library not found. Cannot delete"));
         libraryRepository.deleteLibraryByLibraryCode(libraryCode);
    }

    @Override
    public Optional<Library> findLibraryByLocation(String libraryLocation) {
        String cleanedLibraryLocation = CleanStrings.stripWhiteSpace(libraryLocation);
        return libraryRepository.findLibrariesByLibraryLocation(cleanedLibraryLocation);
    }

    @Override
    public Optional<Library> findLibraryByCode(UUID libraryCode) {
     Library library = libraryRepository.findLibraryByLibraryCode(libraryCode)
                .orElseThrow(() -> new LibraryNotFoundException("Library does not exists"));

        return Optional.of(library);
    }
}
