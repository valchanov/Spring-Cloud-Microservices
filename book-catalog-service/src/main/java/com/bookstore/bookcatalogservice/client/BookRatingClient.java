package com.bookstore.bookcatalogservice.client;

import com.bookstore.bookcatalogservice.model.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "book-rating-service")
public interface BookRatingClient {

    @GetMapping(value = "/ratings/list")
    public List<Rating> getAllBooks();

    @GetMapping(value = "/ratings/{id}")
    public Rating findById(@PathVariable("id") int bookId);

}

