package com.spring.librarydata.repository;

import com.spring.librarydata.dto.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Integer> {
}
