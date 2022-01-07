package com.spring.librarydata;

import com.spring.librarydata.dto.Author;
import com.spring.librarydata.dto.Book;
import com.spring.librarydata.repository.AuthorRepository;
import com.spring.librarydata.repository.BookRepository;
import com.spring.librarydata.repository.CommentRepository;
import lombok.val;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class LibraryDataApplication {

    public static void main(String[] args) {
        val context = SpringApplication.run(LibraryDataApplication.class, args);
        val bookRepo = context.getBean(BookRepository.class);
        val authorRepo = context.getBean(AuthorRepository.class);
        val commentRepo = context.getBean(CommentRepository.class);

        Book book = Book.builder().title("Markiz").build();
        Author author = Author.builder().name("Author").build();
        authorRepo.save(author).setBooks(List.of(book));
        bookRepo.save(book);
        bookRepo.findAll().forEach(System.out::println);

    }
}
