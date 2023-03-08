package com.skypro.library.service;

import com.skypro.library.dao.BookDAO;
import com.skypro.library.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Qualifier("bookDAO")
    private BookDAO bookDAO;

    @Autowired
    public BookServiceImpl(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    @Transactional
    public List<Book> getAllBooks() {
        return bookDAO.getAllBooks();
    }

    @Override
    @Transactional
    public void addBook(Book book) {
        try {
            validateBook(book);
        } catch (IllegalArgumentException e)
        {
            throw new IllegalArgumentException(e.getMessage());
        }
        bookDAO.addBook(book);
    }

    @Override
    public void updateBook(Book book) {
        try {
            validateBook(book);
        } catch (IllegalArgumentException e)
        {
            throw new IllegalArgumentException(e.getMessage());
        }
        bookDAO.updateBook(book);
    }

    @Override
    public void deleteBook(String isbn) {
        bookDAO.deleteBook(isbn);
    }

    private void validateBook(Book book) throws IllegalArgumentException {
        if (book.equals(null)) {
            throw new IllegalArgumentException("Объект \"Книга\" пустой");
        }

        if (book.getName().equals(null)) {
            throw new IllegalArgumentException("Отсутствует заголовок книги");
        }

        if (book.getAuthor().equals(null)) {
            throw new IllegalArgumentException("У книги не указан автор");
        }

        if (book.getYear() < 0) {
            throw new IllegalArgumentException("Год выпуска книги не может быть отрицательным");
        }

        if (!isValidIsbn(book.getIsbn())) {
            throw new IllegalArgumentException("Неправильный ISBN книги");
        }

    }

    public boolean isValidIsbn(String isbn) {
        String str = isbn.replaceAll("-", "");
        if (str.length() != 13) return false;
        if (!str.matches("^[1-9][0-9]*$")) return false;
        int sum = 0;
        for (int i = 0; i < 12; i++) {
            int n = (i % 2 == 0) ? 1 : 3;
            sum += Integer.parseInt(String.valueOf(str.charAt(i))) * n;
        }

        return (sum % 10 == Integer.parseInt(str.substring(12, 13)));

//        for (int i = 3; i < 10; i++) {
//            sum += Integer.parseInt(str.substring(i, i+1)) * (10 - i);
//        }
//
//        return (sum % 11 == 0);
    }
}
