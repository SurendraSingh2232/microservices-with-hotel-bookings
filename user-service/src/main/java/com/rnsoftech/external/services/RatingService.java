package com.rnsoftech.external.services;/*
 * @Created 24/04/2024 - 22:06
 * @User ${"PRAVENDRA KUMAR"}
 */

import com.rnsoftech.domain.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

    @PostMapping("/ratings")
    ResponseEntity<Rating> createRating(Rating values);

    @PutMapping("/ratings/{ratingId}")
    ResponseEntity<Rating> updateRating(@PathVariable Long ratingId, Rating rating);

    @DeleteMapping("/ratings/{ratingId}")
    ResponseEntity<Void> deleteRating(@PathVariable Long ratingId);

}
