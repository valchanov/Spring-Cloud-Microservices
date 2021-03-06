package com.bookstore.bookratingservice.repository;

import com.bookstore.bookratingservice.model.Rating;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends CrudRepository<Rating, Long> {
    public Rating findByBookid(Long bookId);
}
