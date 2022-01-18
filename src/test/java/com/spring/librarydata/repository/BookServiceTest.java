package com.spring.librarydata.repository;

import com.spring.librarydata.dto.Author;
import com.spring.librarydata.dto.Book;
import com.spring.librarydata.dto.Comment;
import com.spring.librarydata.dto.Genre;
import com.spring.librarydata.service.BookService;
import com.spring.librarydata.service.BookServiceImpl;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class BookServiceTest {

    private static final BookRepository bookRepository = mock(BookRepository.class);

    @Autowired
    private BookService bookService;


    @Configuration
    static class ContextConfiguration {
        // this bean will be injected into the OrderServiceTest class
        @Bean
        public BookService bookService() {
            return new BookServiceImpl(bookRepository);
        }
    }

    @DisplayName("Findes book that contains certain comment by its id")
    @Test
    void findBookByCommentId() {

        Book book = Book.builder()
                .id(1)
                .title("Good book")
                .genre(Genre.builder()
                        .id(2)
                        .genre("comedy").build())
                .author(Author.builder()
                        .id(3)
                        .name("Smith")
                        .build())
                .commentList(List.of(
                        Comment.builder().id(1).comment("This book is so nice").build(),
                        Comment.builder().id(2).comment("Another good feedback").build(),
                        Comment.builder().id(3).comment("Awful").build()
                )).build();

        when(bookRepository.findAll()).thenReturn(List.of(book));


        when(bookRepository.findById(1))
                .thenReturn(Optional.ofNullable(book));

        val actual = bookService.findBookByCommentId(1);
        assertThat(actual).usingRecursiveComparison().isEqualTo(Optional.of(book));
    }

    @DisplayName("retuens true if operationa is performed and instance by that id was added")
    @Test
    void addCommentByBookId() {

        int id = 1;
        String comment = "commented something";
        AtomicBoolean expectedResult = new AtomicBoolean(true);
        Book book = Book.builder().id(id).title("nn").commentList(Collections.emptyList()).build();
        when(bookRepository.findById(id)).thenReturn(Optional.ofNullable(book));
        assert book != null;
        when(bookRepository.save(book)).thenReturn(book);

        val result = bookService.addCommentByBookId(id, comment);
        assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);
    }

    @Test
    void save() {
        Book book = Book.builder().id(1)
                .title("Good book")
                .genre(Genre.builder()
                        .id(2)
                        .genre("comedy").build())
                .author(Author.builder()
                        .id(3)
                        .name("Smith")
                        .build())
                .commentList(List.of(
                        Comment.builder().id(1).comment("This book is so nice").build(),
                        Comment.builder().id(2).comment("Another good feedback").build(),
                        Comment.builder().id(3).comment("Awful").build()
                )).build();
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        assertThat(bookService.save(book)).usingRecursiveComparison().isEqualTo(book);
    }

//    void deleteById(int bookId);
    @Test
    void deleteById() {
        ArgumentCaptor<Integer> argument = ArgumentCaptor.forClass(Integer.class);
        doNothing().when(bookRepository).deleteById(argument.capture());
        bookRepository.deleteById(argument.capture());
        verify(bookRepository).deleteById(argument.capture());
        Assertions.assertEquals(0, argument.getValue());
    }

//    Iterable<Book> findAll();
    @Test
    void findAll() {
        List<Book> expected = List.of(Book.builder().build());
        when(bookRepository.findAll()).thenReturn(expected);
        Iterable<Book> actual = bookService.findAll();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void findById() {
        int id = 1;
        Book book = Book.builder().id(id).title("nn").commentList(Collections.emptyList()).build();

        when(bookRepository.findById(id)).thenReturn(Optional.ofNullable(book));
        var actual = bookService.findById(id);
        assertThat(actual).usingRecursiveComparison().isEqualTo(Optional.ofNullable(book));
    }

//  Iterable<Book> findByAuthorId(int authorId);
    @Test
    void findByAuthorId() {
        int id = 1;
        List<Book> expected = Collections.emptyList();
        when(bookRepository.findByAuthor_Id(id)).thenReturn(expected);
        var actual = bookService.findByAuthorId(id);
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }
}