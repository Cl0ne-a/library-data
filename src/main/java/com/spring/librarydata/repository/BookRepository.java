package com.spring.librarydata.repository;

import com.spring.librarydata.dto.Book;
import com.spring.librarydata.dto.Comment;
import com.spring.librarydata.dto.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findByAuthor_Id(int authorId);

    List<Book> findAllByGenre(Genre genre);

    @Query("select b from Book b where b.commentList.size > :minCommentListCount")
    List<Book> findByCommentListGreatedThan( int minCommentListCount);

    @Query("select b.commentList from Book b where b.id = :bookId")
    List<Comment> findCommentListByBookId(int bookId);

    @Transactional
    @Modifying(flushAutomatically = true)
    @Query("update Book b set b.title = :newTitle where b.id = :bookId")
    void updateBook(int bookId, String newTitle);

}
