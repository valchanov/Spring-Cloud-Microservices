package com.bookstore.bookratingservice.service;

import com.bookstore.bookratingservice.model.Rating;
import com.bookstore.bookratingservice.repository.RatingRepository;
import org.springframework.stereotype.Service;

@Service
public class RatingService {
    private RatingRepository ratingRepository;

    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public Rating findByBookid(Long bookId) {
        return ratingRepository.findByBookid(bookId);
    }

    public Iterable<Rating> getAll() {
        return ratingRepository.findAll();
    }

}
