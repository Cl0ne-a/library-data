package com.spring.librarydata;

import com.spring.librarydata.dto.Author;
import com.spring.librarydata.dto.Book;
import com.spring.librarydata.dto.Comment;
import com.spring.librarydata.dto.Genre;
import com.spring.librarydata.service.AuthorService;
import com.spring.librarydata.service.BookService;
import com.spring.librarydata.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
@Controller
public class LibraryController {
    private final GenreService genreService;
    private final AuthorService authorService;
    private final BookService bookService;

    @Autowired
    public LibraryController(GenreService genreService, AuthorService authorService, BookService bookService) {
        this.genreService = genreService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

//    @ShellMethod(key="authors", value = "get all authors")
    Iterable<Author> authorList() {
        return authorService.displayAllAuthors();
    }

//    @ShellMethod(key="genres", value = "get all genres")
    Iterable<Genre> genreList() {
        return genreService.displayAllGenres();
    }

//    @ShellMethod(key="books", value = "get all books")
    Iterable<Book> bookList() {
        return bookService.findAll();
    }

//    @ShellMethod(key = "create-book", value = "create new book, use author and genre that are already in DB")
    Book save(String title, int authorId, int genreId) {
        Book bookToSave= Book.builder().title(title)
                .author(authorService
                        .findById(authorId)
                        .orElseThrow())
                .genre(genreService
                        .findById(genreId)
                        .orElseThrow())
                .build();
        return bookService.save(bookToSave);
    }

//    @ShellMethod(key = "I-say", value = "comment on the book, book will be found by id")
    AtomicBoolean addComment(int bookId, String comment) {
        return bookService.addCommentByBookId(bookId, comment);
    }

//    @ShellMethod(key = "list-comments", value = "list comments book id")
    List<Comment> listCommentsByBookId(int bookId) {
        return bookService.findCommentListByBookId(bookId);
    }

//    @ShellMethod(key = "by-comments", value = "list books by number of comments greater than minimum")
    List<Book> byNumberOfCommentsGreaterThan(int minNumberOfComments) {
        return bookService.findByCommentListGreatedThan(minNumberOfComments);
    }

//    @ShellMethod(key = "update-book",
//            value = "update book by id; returns true if after update the title equals to input title")
    boolean updateBook(int bookId, String title) {
        bookService.updateBook(bookId, title);
        return bookService.findById(bookId).isPresent()
                && bookService.findById(bookId).get().getTitle().equals(title);
    }

//    @ShellMethod(key = "book-by-comment", value = "find book by comment id")
    Book getByComment(int commentId) {
        return bookService.findBookByCommentId(commentId).get();
    }

//    @ShellMethod(key = "delete", value = "delete book by its id")
    void deleteBook(int bookId) {
        bookService.deleteById(bookId);
    }

//    @ShellMethod(key ="book-by-author", value = "list all books by author id")
    Iterable<Book> byAuthorId(int authorId) {
        return bookService.findByAuthorId(authorId);
    }
}
