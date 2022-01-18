package com.spring.librarydata.service;

import com.spring.librarydata.dto.Genre;
import com.spring.librarydata.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService{
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
