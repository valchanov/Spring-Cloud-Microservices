package com.bookstore.bookratingservice.controller;

import com.bookstore.bookratingservice.model.Rating;
import com.bookstore.bookratingservice.service.RatingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ratings")
public class RatingController {
    private RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping("/{bookId}")
    public Rating getRatingByBookId(@PathVariable("bookId") Long id) {
        return ratingService.findByBookid(id);
    }

    @GetMapping("/list")
    public Iterable<Rating> getAllRatings() {
        return ratingService.getAll();
    }
}
