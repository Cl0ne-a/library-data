package com.spring.librarydata;

import com.spring.librarydata.dto.Author;
import com.spring.librarydata.dto.Book;
import com.spring.librarydata.dto.Genre;
import com.spring.librarydata.repository.AuthorRepository;
import com.spring.librarydata.repository.BookRepository;
import com.spring.librarydata.repository.GenreRepository;
import com.spring.librarydata.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@ShellComponent
public class ShellUI {
    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final BookService bookService;

    @Autowired
    public ShellUI(GenreRepository genreRepository, AuthorRepository authorRepository, BookRepository bookRepository, BookService bookService) {
        this.genreRepository = genreRepository;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.bookService = bookService;
    }

    @ShellMethod(key="authors", value = "get all authors")
    Iterable<Author> authorList() {
        return authorRepository.findAll();
    }

    @ShellMethod(key="genres", value = "get all genres")
    Iterable<Genre> genreList() {
        return genreRepository.findAll();
    }

    @ShellMethod(key="books", value = "get all books")
    Iterable<Book> bookList() {
        return bookRepository.findAll();
    }

    @ShellMethod(key ="book-by-author", value = "list all books by author id")
    List<Book> byAuthorId(int authorId) {
        return bookRepository.findByAuthor_Id(authorId);
    }

    @ShellMethod(key = "book-by-genre", value = "list all books by genre id")
    List<Book> byGenreId(int genreId) {
        return bookRepository.findAllByGenre(genreRepository.findById(genreId).orElseThrow());
    }

    @ShellMethod(key = "create-book", value = "create new book, use author and genre that are already in DB")
    Book save(String title, int authorId, int genreId) {
        Book bookToSave= Book.builder().title(title)
                .author(authorRepository
                        .findById(authorId)
                        .orElseThrow())
                .genre(genreRepository
                        .findById(genreId)
                        .orElseThrow())
                .build();
        return bookRepository.save(bookToSave);
    }

    @ShellMethod(key = "by-comments", value = "list books by number of comments greater than minimum")
    List<Book> byNumberOfCommentsGreaterThan(int minNumberOfComments) {
        return bookRepository.findByCommentListGreatedThan(minNumberOfComments);
    }

    @ShellMethod(key = "update-book",
            value = "update book by id; returns true if after update the title equals to input title")
    boolean updateBook(int bookId, String title) {
        bookRepository.updateBook(bookId, title);
        return bookRepository.findById(bookId).isPresent()
                && bookRepository.findById(bookId).get().getTitle().equals(title);
    }

    @ShellMethod(key = "I-say", value = "comment on the book, book will be found by id")
    AtomicBoolean addComment(int bookId, String comment) {
        return bookService.addCommentByBookId(bookId, comment);
    }

    @ShellMethod(key = "book-by-comment", value = "find book by comment id")
    Book getByComment(int commentId) {
        return bookService.findBookByCommentId(commentId).get();
    }

    @ShellMethod(key = "delete", value = "delete book by its id")
    void deleteBook(int bookId) {
        bookRepository.deleteById(bookId);
    }
}
