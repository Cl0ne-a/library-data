package com.spring.librarydata.repository;

import com.spring.librarydata.dto.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Integer> {
}
