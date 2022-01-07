package com.spring.librarydata.repository;

import com.spring.librarydata.dto.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Integer> {
}
