package com.bookstore.bookratingservice.service;

import com.bookstore.bookratingservice.model.Rating;
import com.bookstore.bookratingservice.repository.RatingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingService {
    private RatingRepository ratingRepository;

    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public Optional<Rating> getRating(Long bookId) {
        return ratingRepository.findById(bookId);
    }

    public Iterable<Rating> getAll() {
        return ratingRepository.findAll();
    }

}
