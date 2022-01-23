package com.spring.librarydata.service.impl;

import com.spring.librarydata.dto.Author;
import com.spring.librarydata.repository.AuthorRepository;
import com.spring.librarydata.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutuhorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @Autowired
    public AutuhorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Iterable<Author> displayAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(int id) {
        return authorRepository.findById(id);
    }
}
