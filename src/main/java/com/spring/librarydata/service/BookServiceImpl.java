package com.spring.librarydata.service;

import com.spring.librarydata.dto.Book;
import com.spring.librarydata.dto.Comment;
import com.spring.librarydata.dto.Genre;
import com.spring.librarydata.repository.BookRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class BookServiceImpl implements BookService {
    public final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
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
        val specialComment =bookRepository.findAll().stream()
                .map(Book::getCommentList)
                .flatMap(comments -> comments.stream().filter(comment -> comment.getId() == commentId)).findFirst().get();
        int bookId = 0;

        for (Book book: bookRepository.findAll()) {
            if (book.getCommentList().contains(specialComment)) {
                bookId = book.getId();
                break;
            }
            else {
                System.out.println("no comment found");
            }
        }
        return bookRepository.findById(bookId);
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
        bookRepository.updateBook(bookId, newTitle);
    }

    @Override
    public List<Book> findByAuthorId(int authorId) {
        return bookRepository.findByAuthor_Id(authorId);
    }

    @Override
    public List<Book> findAllByGenre(Genre genre) {
        return bookRepository.findAllByGenre(genre);
    }

    @Override
    public List<Book> findByCommentListGreatedThan(int minCommentListCount) {
        return bookRepository.findByCommentListGreatedThan(minCommentListCount);
    }

    @Override
    public List<Comment> findCommentListByBookId(int bookId) {
        return bookRepository.findCommentListByBookId(bookId);
    }
}
