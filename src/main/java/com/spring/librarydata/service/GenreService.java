package com.spring.librarydata.service;

import com.spring.librarydata.entity.Genre;

import java.util.Optional;

public interface GenreService {
    Iterable<Genre> displayAllGenres();
    Optional<Genre> findById(int id);
}
