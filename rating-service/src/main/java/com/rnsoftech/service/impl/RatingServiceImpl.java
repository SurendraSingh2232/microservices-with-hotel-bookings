package com.rnsoftech.service.impl;/*
 * @Created 24/04/2024 - 09:53
 * @User ${"PRAVENDRA KUMAR"}
 */

import com.rnsoftech.domain.Rating;
import com.rnsoftech.repository.RatingRepository;
import com.rnsoftech.service.RatingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RatingServiceImpl implements RatingService {

    private final Logger log = LoggerFactory.getLogger(RatingServiceImpl.class);
    private final RatingRepository ratingRepository;

    public RatingServiceImpl(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @Override
    public Rating createRating(Rating rating) {
        log.info("Service Request to createRating: {}", rating);
        Rating result = ratingRepository.save(rating);
        return result;
    }

    @Override
    public List<Rating> getRatings() {
        log.info("Service Request to getRatings: {}");
        List<Rating> result = ratingRepository.findAll();
        return result;
    }

    @Override
    public List<Rating> getRatingByUserId(Long userId) {
        log.info("Service Request to getRatingByUserId: {}", userId);
        List<Rating> result = ratingRepository.findByUserId(userId);
        return result;
    }

    @Override
    public List<Rating> getRatingByHotelId(Long hotelId) {
        log.info("Service Request to v: {}", hotelId);
        List<Rating> result = ratingRepository.findByHotelId(hotelId);
        return result;
    }
}
