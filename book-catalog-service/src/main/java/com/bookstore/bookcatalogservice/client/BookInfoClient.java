package com.bookstore.bookcatalogservice.client;

import com.bookstore.bookcatalogservice.model.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "book-info-service")
public interface BookInfoClient {

    @GetMapping(value = "/books/list")
    public List<Book> getAllBooks();

    @GetMapping(value = "/books/{id}")
    public Book findById(@PathVariable("id") int bookId);
}
