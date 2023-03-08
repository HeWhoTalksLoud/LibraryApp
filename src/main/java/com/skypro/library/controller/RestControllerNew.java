package com.skypro.library.controller;

import com.skypro.library.entity.Book;
import com.skypro.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skypro")
public class RestControllerNew {
    private BookService bookService;

    @Autowired
    public RestControllerNew(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/api/book")
    public List<Book> getBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping("/api/book")
    public Book addBook(@RequestBody Book book) {
        bookService.addBook(book);
        return book;
    }

    @PutMapping("/api/book")
    public Book updateBook(@RequestBody Book book) {
        bookService.updateBook(book);
        return book;
    }

    @DeleteMapping("/api/book")
    public void deleteBook(@RequestParam String isbn) {
        bookService.deleteBook(isbn);
    }

    // Для проверки проверки ISBN
    @GetMapping("/api/isbn")
    public String checkIsbn(@RequestParam String isbn) {
        return isbn + (bookService.isValidIsbn(isbn) ? " - корректный ISBN" : " - некорректный ISBN");
    }


}
