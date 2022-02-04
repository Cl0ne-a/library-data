package com.spring.librarydata;

import com.spring.librarydata.dto.BookDto;
import com.spring.librarydata.entity.Book;
import com.spring.librarydata.service.AuthorService;
import com.spring.librarydata.service.BookService;
import com.spring.librarydata.service.GenreService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("library/genres")
    public String listPageGenres(Model model) {
        val genres = genreService.displayAllGenres();
        model.addAttribute("genres", genres);
        return "genres";
    }

    @GetMapping("/library/authors")
    public String listPageAuthors(Model model) {
        val authors = authorService.displayAllAuthors();
        model.addAttribute("authors", authors);
        return "authors";
    }

    @GetMapping("/library/books")
    public String listPage(Model model) {
        val books = bookService.findAll();
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/library/edit")
    public String editPage(@RequestParam("id") int id, Model model) {
        val book = bookService.findById(id);
        model.addAttribute("book", book);
        return "edit";
    }

    @PostMapping("/library/edit")
    public String saveBook(BookDto bookDto) {
        bookService.save(bookDto);
        return "redirect:/library/books";
    }
}
