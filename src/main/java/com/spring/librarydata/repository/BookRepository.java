package com.spring.librarydata.repository;

import com.spring.librarydata.dto.Book;
import com.spring.librarydata.dto.Comment;
import com.spring.librarydata.dto.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query("select b from Book b where b.author.id = :authorId")
    List<Book> findByAuthor_Id(int authorId);

    @Query("select b from Book b where b.genre = :genre")
    List<Book> findAllByGenre(Genre genre);

    @Query("select b from Book b where b.commentList.size > :minCommentListCount")
    List<Book> findByCommentListGreaterThan(int minCommentListCount);

    @Query("select b.commentList from Book b where b.id = :bookId")
    List<Comment> findCommentListByBookId(int bookId);

    @Modifying(flushAutomatically = true)
    @Query("update Book b set b.title = :newTitle where b.id = :bookId")
    void updateBook(int bookId, String newTitle);

    void deleteById(int Id);

    @Query("select b from Book b")
    List<Book> findAll();

    @Query("select b from Book b join fetch b.commentList")
    Optional<Book> findById(int id);

    @Query("select b from Book b join fetch b.commentList")
    List<Book> findBy(int specialCommentId);
}
