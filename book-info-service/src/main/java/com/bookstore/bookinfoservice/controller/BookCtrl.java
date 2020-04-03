package com.bookstore.bookinfoservice.controller;

import com.bookstore.bookinfoservice.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookCtrl {
    private BookService bookService;

    public BookCtrl (BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping("/{bookId}")
    public Optional getBookInfo(@PathVariable("bookId") Long id) {
        return bookService.findBookById(id);
    }

    @GetMapping("/list")
    public Iterable getBooksInfo(){
        return bookService.findAllBooks();
    }

    @GetMapping("/test")
    public String getList(){
        return "Book info list of all books";
    }
}
