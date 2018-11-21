package com.ajouict.inhousekitchen.service;

import com.ajouict.inhousekitchen.domain.Review;
import com.ajouict.inhousekitchen.domain.ReviewRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository){
        this.reviewRepository = reviewRepository;
    }

    public Page<Review> findReviewList(Pageable pageable){
        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1, pageable.getPageSize());
        return reviewRepository.findAll(pageable);
    }

    public Review findReviewByIdx(Long idx){
        return reviewRepository.findById(idx).orElse(new Review());
    }
}
