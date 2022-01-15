package com.spring.librarydata.repository;

import com.spring.librarydata.dto.Comment;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CommentRepository extends PagingAndSortingRepository<Comment, Integer> {
}
