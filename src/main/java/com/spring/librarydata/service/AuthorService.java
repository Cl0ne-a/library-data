package com.spring.librarydata.service;

import com.spring.librarydata.entity.Author;

import java.util.Optional;


public interface AuthorService {
    Iterable<Author> displayAllAuthors();
    Optional<Author> findById(int id);
}
