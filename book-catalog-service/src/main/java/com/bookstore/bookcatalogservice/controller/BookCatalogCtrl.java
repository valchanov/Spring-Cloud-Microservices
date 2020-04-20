package com.bookstore.bookcatalogservice.controller;

import com.bookstore.bookcatalogservice.model.Book;
import com.bookstore.bookcatalogservice.model.CatalogItem;
import com.bookstore.bookcatalogservice.model.Rating;
import com.bookstore.bookcatalogservice.client.BookInfoClient;
import com.bookstore.bookcatalogservice.client.BookRatingClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/catalog")
public class BookCatalogCtrl {
    private BookInfoClient bookInfoClient;
    private BookRatingClient bookRatingClient;

    public BookCatalogCtrl(BookInfoClient bookInfoClient,
                           BookRatingClient bookRatingClient) {
        this.bookInfoClient = bookInfoClient;
        this.bookRatingClient = bookRatingClient;
    }

    @GetMapping("/list")
    @HystrixCommand(defaultFallback = "getFallbackCatalogue",
            commandProperties = {
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "500"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "2"),
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
            }
    )
    public List<CatalogItem> getCatalog() {
        List<Book> booksInfo = bookInfoClient.getAllBooks();
        List<Rating> booksRating = bookRatingClient.getAllBooks();

        List<CatalogItem> catalog = new ArrayList<>();

        (booksInfo).forEach(b -> {
            int currentRating = booksRating.stream()
                    .filter(r -> r.getBookid().equals(b.getId()))
                    .findFirst()
                    .get()
                    .getRating();

            catalog.add(new CatalogItem(b.getTitle(), b.getAuthor(), b.getDescription(), currentRating));
        });

        return catalog;
    }


    @GetMapping("/{bookId}")
    public CatalogItem getCatalogItem(@PathVariable("bookId") Integer bookId) {

        Book book = bookInfoClient.findById(bookId);
        Rating rating = bookRatingClient.findById(bookId);

        return new CatalogItem(book.getTitle(), book.getAuthor(), book.getDescription(), rating.getRating());
    }

    private List<CatalogItem> getFallbackCatalogue() {
        return Collections.singletonList(new CatalogItem("No books found", "", "", 0));
    }
}

