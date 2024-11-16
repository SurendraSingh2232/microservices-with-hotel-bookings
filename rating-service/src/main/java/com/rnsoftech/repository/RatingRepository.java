package com.rnsoftech.repository;/*
 * @Created 24/04/2024 - 09:50
 * @User ${"PRAVENDRA KUMAR"}
 */

import com.rnsoftech.domain.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    List<Rating> findByUserId(Long userId);
    List<Rating> findByHotelId(Long hotelId);
}
