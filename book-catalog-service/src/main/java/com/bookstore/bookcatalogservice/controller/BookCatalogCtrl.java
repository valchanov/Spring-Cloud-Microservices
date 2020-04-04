package com.bookstore.bookcatalogservice.controller;

import com.bookstore.bookcatalogservice.model.Book;
import com.bookstore.bookcatalogservice.model.CatalogItem;
import com.bookstore.bookcatalogservice.model.Rating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
@RequestMapping("/catalog")
public class BookCatalogCtrl {
    private RestTemplate restTemplate;

    public BookCatalogCtrl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
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
        Book[] books = restTemplate.getForEntity("http://book-info-service/books/list",
                Book[].class).getBody();

        Rating[] ratings = restTemplate.getForEntity("http://book-rating-service/ratings/list",
                Rating[].class).getBody();

        List<CatalogItem> catalog = new ArrayList<>();

        Arrays.stream(books).forEach(b -> {
            int currentRating = Arrays.stream(ratings)
                    .filter(r -> r.getBookid().equals(b.getId()))
                    .findFirst()
                    .get()
                    .getRating();

            catalog.add(new CatalogItem(b.getTitle(), b.getAuthor(), b.getDescription(), currentRating));
        });

        return catalog;
    }


    @GetMapping("/{bookId}")
    public CatalogItem getCatalogItem(@PathVariable("bookId") String bookId) {

        Book book = restTemplate.getForObject("http://book-info-service/books/" + bookId,
                Book.class);

        Rating rating = restTemplate.getForObject("http://book-rating-service/ratings/" + bookId,
                Rating.class);

        return new CatalogItem(book.getTitle(), book.getAuthor(), book.getDescription(), rating.getRating());
    }

    private List<CatalogItem> getFallbackCatalogue() {
        return Collections.singletonList(new CatalogItem("No books found", "", "", 0));
    }
}

