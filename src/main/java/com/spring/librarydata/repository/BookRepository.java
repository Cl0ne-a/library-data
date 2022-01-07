package com.spring.librarydata.repository;

import com.spring.librarydata.dto.Author;
import com.spring.librarydata.dto.Book;
import com.spring.librarydata.dto.Genre;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Integer> {
    List<Book> findAllByGenre(Genre genre);

//    @Query("select b.TITLE from BOOK b join AUTHOR a on a.NAME = :name")
    List<Book> findByAuthor(Author author);
}
