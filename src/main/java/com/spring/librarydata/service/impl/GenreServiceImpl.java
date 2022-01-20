package com.spring.librarydata.service.impl;

import com.spring.librarydata.dto.Genre;
import com.spring.librarydata.repository.GenreRepository;
import com.spring.librarydata.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;

    @Autowired
    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public Iterable<Genre> displayAllGenres() {
        return genreRepository.findAll();
    }

    @Override
    public Optional<Genre> findById(int id) {
        return genreRepository.findById(id);
    }
}
