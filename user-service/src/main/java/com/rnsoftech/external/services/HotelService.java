package com.rnsoftech.external.services;/*
 * @Created 24/04/2024 - 21:47
 * @User ${"PRAVENDRA KUMAR"}
 */

import com.rnsoftech.domain.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "HOTEL-SERVICE")
public interface HotelService {

    @GetMapping("/hotels/{hotelId}")
    Hotel getHotel(@PathVariable Long hotelId);

    @GetMapping("/hotels")
    List<Hotel> getAllHotels();
}
