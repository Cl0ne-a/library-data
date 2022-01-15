package com.spring.librarydata.repository;

import com.spring.librarydata.dto.Author;
import com.spring.librarydata.dto.Book;
import com.spring.librarydata.dto.Comment;
import com.spring.librarydata.dto.Genre;
import com.spring.librarydata.service.BookService;
import com.spring.librarydata.service.BookServiceImpl;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;

@SpringBootTest
class BookServiceTest {
    private final BookRepository bookRepository;
    private TestEntityManager entityManager;

    @Autowired
    private final BookService bookService;

    BookServiceTest() {
        this.bookRepository = mock(BookRepository.class);
        this.bookService = new BookServiceImpl(bookRepository);
    }

    @DisplayName("Findes book that contains certain comment by its id")
    @Test
    void Test() {
        val expected = ((BookServiceImpl) bookService).bookRepository.findById(1).get();
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
        val actual = bookService.findBookByCommentId(1).get();
        assertThat(actual).usingRecursiveComparison().isEqualTo(book);
    }

    @DisplayName("retuens true if operationa is performed and instance by that id was added")
    @Test
    void test() {
        int id = 1;
        String comment = "commented something";
        AtomicBoolean expectedResult = new AtomicBoolean(true);
        val result = bookService.addCommentByBookId(id, comment);
        assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);
    }
}