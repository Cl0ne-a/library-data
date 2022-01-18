package com.spring.librarydata.service;

import com.spring.librarydata.dto.Author;
import com.spring.librarydata.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutuhorServiceImpl implements AuthorService{
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
