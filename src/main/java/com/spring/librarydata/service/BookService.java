package com.spring.librarydata.service;

import com.spring.librarydata.dto.Book;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public interface BookService {
    AtomicBoolean addCommentByBookId(int bookId, String commentLine);

    Optional<Book> findBookByCommentId(int commentId);
}
