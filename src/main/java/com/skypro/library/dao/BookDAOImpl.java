package com.skypro.library.dao;

import com.skypro.library.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class BookDAOImpl implements BookDAO {


    // сделать RowMapper

    private final JdbcTemplate jdbcTemplate;
//    @Autowired
//    JdbcTemplate jdbcTemplate() {
//        return new JdbcTemplate();
//    }


    @Autowired
    public BookDAOImpl(@Lazy JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addBook(Book book) {
        String sql = "INSERT INTO books(author, name, isbn, year)" +
                "VALUES (?, ?, ?, ?)";

        jdbcTemplate.update(sql, book.getAuthor(), book.getName(), book.getIsbn(), book.getYear());
    }

    @Override
    public void updateBook(Book book) {
        String sql = "UPDATE books SET author = ?, name = ?, year = ? WHERE isbn = ?";

        jdbcTemplate.update(sql, book.getAuthor(), book.getName(), book.getYear(), book.getIsbn());

    }

    @Override
    public void deleteBook(String ISBN) {
        String sql = "DELETE FROM books WHERE isbn = ?";

        jdbcTemplate.update(sql, ISBN);

    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> books;

        String sql = "SELECT * FROM books ORDER BY name";

        books = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class));

        return books;
    }

    @Override
    public Book getBook(Integer ISBN) {
        String sql = "SELECT * FROM books WHERE isbn = ?";

        return  jdbcTemplate.queryForObject(sql, new Object[] { ISBN }, new BeanPropertyRowMapper<>(Book.class));
    }
}
