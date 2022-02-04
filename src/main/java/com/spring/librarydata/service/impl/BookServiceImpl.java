package com.spring.librarydata.service.impl;

import com.spring.librarydata.dto.BookDto;
import com.spring.librarydata.entity.Book;
import com.spring.librarydata.entity.Comment;
import com.spring.librarydata.repository.BookRepository;
import com.spring.librarydata.repository.CommentRepository;
import com.spring.librarydata.service.BookService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    public final BookRepository bookRepository;
    public final CommentRepository commentRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, CommentRepository commentRepository) {
        this.bookRepository = bookRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public BookDto save(BookDto bookDto) {
        val book = BookDto.toDomainObject(bookDto);
        val newBook = bookRepository.save(book);
        return BookDto.toBookDto(newBook);
    }

    @Override
    public Iterable<BookDto> findAll() {
        val bookList = bookRepository.findAll();
        return bookList.stream().map(BookDto::toBookDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookDto findById(int id) {
        val book = bookRepository.findById(id).orElseThrow(RuntimeException::new);
        return BookDto.toBookDto(book);
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
    public void deleteById(int bookId) {
        bookRepository.deleteById(bookId);
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
}
