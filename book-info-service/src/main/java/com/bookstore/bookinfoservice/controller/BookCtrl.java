package com.bookstore.bookinfoservice.controller;

import com.bookstore.bookinfoservice.service.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookCtrl {
    private BookService bookService;

    public BookCtrl(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{bookId}")
    public Optional getBookInfo(@PathVariable("bookId") Long id) {
        return bookService.findBookById(id);
    }

    @GetMapping("/list")
    public Iterable getBooksInfo() {
        return bookService.findAllBooks();
    }
}
