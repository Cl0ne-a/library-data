package com.spring.librarydata.initializer;

import com.spring.librarydata.entity.Author;
import com.spring.librarydata.entity.Book;
import com.spring.librarydata.entity.Comment;
import com.spring.librarydata.entity.Genre;
import com.spring.librarydata.repository.AuthorRepository;
import com.spring.librarydata.repository.BookRepository;
import com.spring.librarydata.repository.GenreRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class IinitialDataLoader {

    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;


    public IinitialDataLoader(GenreRepository genreRepository,
                              AuthorRepository authorRepository,
                              BookRepository bookRepository) {
        this.genreRepository = genreRepository;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;

    }

    @PostConstruct
    void loadData() {
        Genre drama = Genre.builder().genre("drama").build();
        Genre comedy = Genre.builder().genre("comedy").build();
        Genre horror = Genre.builder().genre("horror").build();
        Genre science =  Genre.builder().genre("science").build();

        genreRepository.saveAll(List.of(drama, comedy, horror, science));

        Author authorSmith = Author.builder().name("Smith").build();
        Author authorJohnson = Author.builder().name("Johnson").build();
        Author authorElvin = Author.builder().name("Elvin").build();
        Author authorNeiman = Author.builder().name("Fon Neiman").build();

        authorRepository.saveAll(List.of(authorElvin, authorJohnson, authorSmith, authorNeiman));

        Comment goodBookComment = Comment.builder()
                .comment("This book is so nice").build();
        Comment secondGoodBookComment = Comment.builder()
                .comment("Another good feedback").build();
        Comment thirdBookComment = Comment.builder()
                .comment("Could be batter").build();
        Comment badBookComment = Comment.builder()
                .comment("Awful").build();

        Book goodBook = Book.builder()
                .title("Good book")
                .author(authorSmith)
                .commentList(List.of(goodBookComment, secondGoodBookComment, badBookComment ))
                .genre(comedy).build();


        Book badBook = Book.builder()
                .title("Bad book")
                .author(authorElvin)
                .genre(drama)
                .commentList(List.of(thirdBookComment))
                .build();

        Book scienceBook = Book.builder()
                .title("Theory of Games and Economic Behaviour")
                .genre(science)
                .author(authorNeiman)
                .build();


        bookRepository.saveAll(List.of(goodBook, badBook, scienceBook));
    }
}
