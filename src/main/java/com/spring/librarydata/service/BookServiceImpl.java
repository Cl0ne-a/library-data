package com.spring.librarydata.service;

import com.spring.librarydata.dto.Book;
import com.spring.librarydata.dto.Comment;
import com.spring.librarydata.repository.BookRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
