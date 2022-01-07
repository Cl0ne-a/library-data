package com.spring.librarydata;

import com.spring.librarydata.dto.Author;
import com.spring.librarydata.dto.Book;
import com.spring.librarydata.dto.Comment;
import com.spring.librarydata.dto.Genre;
import com.spring.librarydata.repository.AuthorRepository;
import com.spring.librarydata.repository.BookRepository;
import com.spring.librarydata.repository.CommentRepository;
import com.spring.librarydata.repository.GenreRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class IinitialDataLoader {

    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final CommentRepository commentRepository;

    public IinitialDataLoader(GenreRepository genreRepository, AuthorRepository authorRepository, BookRepository bookRepository, CommentRepository commentRepository) {
        this.genreRepository = genreRepository;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.commentRepository = commentRepository;
    }

    @PostConstruct
    void loadData() {
        Genre drama = Genre.builder().genre("drama").build();
        Genre comedy = Genre.builder().genre("comedy").build();
        Genre horror = Genre.builder().genre("horror").build();

        genreRepository.saveAll(List.of(drama, comedy, horror));

        Author authorSmith = Author.builder().name("Smith").build();
        Author authorJohnson = Author.builder().name("Johnson").build();
        Author authorElvin = Author.builder().name("Elvin").build();

        authorRepository.saveAll(List.of(authorElvin, authorJohnson, authorSmith));

        Book goodBook = Book.builder()
                .title("Good book")
                .author(authorSmith)
                .genre(comedy).build();
        Comment goodBookComment = Comment.builder()
                .book(goodBook)
                .comment("This book is so nice").build();

        Book badBook = Book.builder()
                .title("Bad book")
                .author(authorElvin)
                .genre(drama).build();

        bookRepository.saveAll(List.of(goodBook, badBook));
        commentRepository.saveAll(List.of(goodBookComment));
    }
}
