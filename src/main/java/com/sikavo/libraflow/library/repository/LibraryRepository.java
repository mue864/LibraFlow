package com.sikavo.libraflow.library.repository;

import com.sikavo.libraflow.book.Book;
import com.sikavo.libraflow.library.model.Library;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface LibraryRepository extends JpaRepository<Library, UUID> {
    @Override
    List<Library> findAll(@NonNull Sort sort);

    List<Library> findLibraryByLibraryName(String libraryName);

//    List<Library> findLibraryByBookList(List<Book> bookList);

    Optional<Library> findLibraryByLibraryCode(UUID libraryCode);

    Optional<Library> findLibrariesByLibraryLocation(String libraryLocation);

    void deleteLibraryByLibraryCode(UUID libraryCode);
}
