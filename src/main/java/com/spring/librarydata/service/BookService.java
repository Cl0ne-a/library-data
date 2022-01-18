package com.spring.librarydata.service;

import com.spring.librarydata.dto.Book;
import com.spring.librarydata.dto.Comment;
import com.spring.librarydata.dto.Genre;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public interface BookService {
    AtomicBoolean addCommentByBookId(int bookId, String commentLine);

    Optional<Book> findBookByCommentId(int commentId);

    Book save(Book book);

    void deleteById(int bookId);

    Iterable<Book> findAll();

    Optional<Book> findById(int id);
    void updateBook(int bookId, String newTitle);

    Iterable<Book> findByAuthorId(int authorId);

    List<Book> findAllByGenre(Genre genre);

    List<Book> findByCommentListGreatedThan( int minCommentListCount);

    List<Comment> findCommentListByBookId(int bookId);
}
