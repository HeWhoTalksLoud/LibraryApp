package com.skypro.library.entity;


import javax.persistence.*;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @Column(name = "isbn")
    private String isbn;
    @Column(name = "name")
    private String name;
    @Column(name = "author")
    private String author;
    @Column(name = "year")
    private Integer year;

    public Book(String isbn, String name, String author, Integer year) {
        this.isbn = isbn;
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public Book() {

    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
