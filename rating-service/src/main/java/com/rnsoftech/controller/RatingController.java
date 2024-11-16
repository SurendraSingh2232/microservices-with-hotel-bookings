package com.rnsoftech.controller;/*
 * @Created 24/04/2024 - 10:03
 * @User ${"PRAVENDRA KUMAR"}
 */

import com.rnsoftech.domain.Rating;
import com.rnsoftech.repository.RatingRepository;
import com.rnsoftech.service.RatingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {
    private final Logger log = LoggerFactory.getLogger(RatingController.class);
    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    // create
    @PostMapping
    public ResponseEntity<Rating> create(@RequestBody Rating rating) {
        log.info("REST Request to create: {}", rating);
        return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.createRating(rating));
    }

    @GetMapping
    public ResponseEntity<List<Rating>> getRatings() {
        log.info("REST Request to getRatings: {}");
        return ResponseEntity.ok(ratingService.getRatings());
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable Long userId) {
        log.info("REST Request to getRatingsByUserId: {}", userId);
        return ResponseEntity.ok(ratingService.getRatingByUserId(userId));
    }

    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingsByHotelId(@PathVariable Long hotelId) {
        log.info("REST Request to getRatingsByHotelId: {}", hotelId);
        return ResponseEntity.ok(ratingService.getRatingByHotelId(hotelId));
    }
}
