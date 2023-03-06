package com.skypro.library.controller;

import com.skypro.library.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/skypro")
public class MvcController {

    private BookService bookService;

    public MvcController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/web")
    public String getBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());

        return "dashboard";
    }
}
