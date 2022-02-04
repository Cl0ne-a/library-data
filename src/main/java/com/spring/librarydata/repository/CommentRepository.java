package com.spring.librarydata.repository;

import com.spring.librarydata.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    @Query("select c from Comment c join fetch c.book")
    List<Comment> findAll();
}
