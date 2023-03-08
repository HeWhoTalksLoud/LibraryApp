package com.skypro.library.dao;

import com.skypro.library.entity.Book;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface BookDAO {
    void addBook(Book book);
    void updateBook(Book book);
    void deleteBook(String ISBN);
    List<Book> getAllBooks();
    Book getBook(Integer ISBN);
}
