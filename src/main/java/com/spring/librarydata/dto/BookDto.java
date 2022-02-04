package com.spring.librarydata.dto;


import com.spring.librarydata.entity.Author;
import com.spring.librarydata.entity.Book;
import com.spring.librarydata.entity.Comment;
import com.spring.librarydata.entity.Genre;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Builder
@Data
public class BookDto implements Serializable {
    int id;
    String title;
    Author author;
    Genre genre;
    List<Comment> list;

    public static Book toDomainObject(BookDto bookDto) {
        return new Book(bookDto.getId(),
                bookDto.getTitle(),
                bookDto.getAuthor(),
                bookDto.getGenre(),
                bookDto.getList());
    }

    public static BookDto toBookDto(Book book) {
        return new BookDto(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getGenre(),
                book.getCommentList());
    }
}
