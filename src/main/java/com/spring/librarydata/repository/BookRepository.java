package com.spring.librarydata.repository;

import com.spring.librarydata.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {

//    @Query("select b from Book b join fetch b.commentList where b.id = :id")
//    Optional<Book> findById(Integer id);
}
