package com.spring.librarydata.repository;

import com.spring.librarydata.dto.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query("select b from Book b where b.commentList.size > :minCommentListCount")
    List<Book> findByCommentListGreaterThan(int minCommentListCount);

    @Query("select b from Book b join fetch b.commentList where b.id = :id")
    Optional<Book> findById(int id);
}
