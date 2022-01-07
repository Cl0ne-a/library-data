package com.spring.librarydata;

import com.spring.librarydata.dto.Author;
import com.spring.librarydata.dto.Book;
import com.spring.librarydata.dto.Genre;
import com.spring.librarydata.repository.AuthorRepository;
import com.spring.librarydata.repository.BookRepository;
import com.spring.librarydata.repository.CommentRepository;
import com.spring.librarydata.repository.GenreRepository;
import lombok.val;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibraryDataApplication {

    public static void main(String[] args) {
        val context = SpringApplication.run(LibraryDataApplication.class, args);
        val bookRepo = context.getBean(BookRepository.class);
        val authorRepo = context.getBean(AuthorRepository.class);
        val genreRepo = context.getBean(GenreRepository.class);
        val commentRepo = context.getBean(CommentRepository.class);

        authorRepo.findAll().forEach(System.out::println);
        genreRepo.findAll().forEach(System.out::println);
        bookRepo.findAll().forEach(System.out::println);
        commentRepo.findAll().forEach(System.out::println);
    }
}
