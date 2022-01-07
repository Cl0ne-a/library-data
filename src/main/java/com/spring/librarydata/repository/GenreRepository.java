package com.spring.librarydata.repository;

import com.spring.librarydata.dto.Genre;
import org.springframework.data.repository.CrudRepository;

public interface GenreRepository extends CrudRepository<Genre, Integer> {
}
