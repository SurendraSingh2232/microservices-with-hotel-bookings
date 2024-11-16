package com.rnsoftech.repository;/*
 * @Created 23/04/2024 - 10:45
 * @User ${"PRAVENDRA KUMAR"}
 */

import com.rnsoftech.domain.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
}
