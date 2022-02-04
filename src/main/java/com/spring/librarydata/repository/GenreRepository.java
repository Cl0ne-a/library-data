package com.spring.librarydata.repository;

import com.spring.librarydata.entity.Genre;
import org.springframework.data.repository.CrudRepository;

public interface GenreRepository extends CrudRepository<Genre, Integer> {
}
