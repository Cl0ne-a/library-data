package com.spring.librarydata.service.impl;

import com.spring.librarydata.dto.Book;
import com.spring.librarydata.dto.Comment;
import com.spring.librarydata.dto.Genre;
import com.spring.librarydata.repository.BookRepository;
import com.spring.librarydata.repository.CommentRepository;
import com.spring.librarydata.service.BookService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Service
public class LibraryServiceImpl implements BookService {
    public final BookRepository bookRepository;
    public final CommentRepository commentRepository;

    @Autowired
    public LibraryServiceImpl(BookRepository bookRepository, CommentRepository commentRepository) {
        this.bookRepository = bookRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public AtomicBoolean addCommentByBookId(int bookId, String commentLine) {
        val newComment = Comment.builder().comment(commentLine).build();
        Optional<Book> book = bookRepository.findById(bookId);
        AtomicBoolean isSaved = new AtomicBoolean(false);

        book.ifPresent(b -> {
            b.getCommentList().add(newComment);
            bookRepository.save(b);
            isSaved.set(true);
        });

        return isSaved;
    }

    @Override
    public Optional<Book> findBookByCommentId(int commentId) {
        val comments = commentRepository.findAll();
        return comments.stream().filter(comment -> comment.getId() == commentId).map(Comment::getBook).findFirst();
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void deleteById(int bookId) {
        bookRepository.deleteById(bookId);
    }

    @Override
    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(int id) {
        return bookRepository.findById(id);
    }

    @Transactional
    @Override
    public void updateBook(int bookId, String newTitle) {
        bookRepository.findById(bookId).ifPresent(book -> book.setTitle(newTitle));
    }

    @Override
    public List<Book> findByAuthorId(int authorId) {
        return bookRepository.findAll()
                .stream()
                .filter(book
                        -> book.getAuthor().getId() == authorId)
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> findAllByGenre(Genre genre) {
        return bookRepository.findAll()
                .stream()
                .filter(book
                        -> book.getGenre() == genre)
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> findByCommentListGreatedThan(int minCommentListCount) {
        return bookRepository.findByCommentListGreaterThan(minCommentListCount);
    }

    @Override
    public List<Comment> findCommentListByBookId(int bookId) {

        return bookRepository.findById(bookId).isPresent()
                ? bookRepository.findById(bookId).get().getCommentList()
                : null;
    }
}
