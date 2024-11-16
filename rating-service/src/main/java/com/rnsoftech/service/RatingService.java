package com.rnsoftech.service;/*
 * @Created 24/04/2024 - 09:51
 * @User ${"PRAVENDRA KUMAR"}
 */

import com.rnsoftech.domain.Rating;

import java.util.List;

public interface RatingService {

    Rating createRating(Rating rating);

    List<Rating> getRatings();

    List<Rating> getRatingByUserId(Long userId);

    List<Rating> getRatingByHotelId(Long hotelId);
}
