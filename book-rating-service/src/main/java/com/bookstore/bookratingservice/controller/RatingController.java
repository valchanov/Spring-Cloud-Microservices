package com.bookstore.bookratingservice.controller;

import com.bookstore.bookratingservice.model.Rating;
import com.bookstore.bookratingservice.service.RatingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/ratings")
public class RatingController {
    private RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping("/test")
    public Iterable<Rating> getAll() {
        return ratingService.getAll();
    }

    @GetMapping("/test2")
    public String aaa() {
        return "This is test";
    }

    @GetMapping("/{bookId}")
    public Optional<Rating> getRating(@PathVariable("bookId") Long id){
        return ratingService.getRating(id);
    }

    @GetMapping("/list")
    public Iterable<Rating> getAllRatings(){
        return ratingService.getAll();
    }
}
