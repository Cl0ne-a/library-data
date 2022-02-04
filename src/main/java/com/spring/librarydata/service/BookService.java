package com.spring.librarydata.service;

import com.spring.librarydata.dto.BookDto;
import com.spring.librarydata.entity.Book;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public interface BookService {
    Iterable<BookDto> findAll();

    BookDto findById(int id);

    BookDto save(BookDto bookDto);

    AtomicBoolean addCommentByBookId(int bookId, String commentLine);

    Optional<Book> findBookByCommentId(int commentId);

    void deleteById(int bookId);

    void updateBook(int bookId, String newTitle);

    Iterable<Book> findByAuthorId(int authorId);

}
