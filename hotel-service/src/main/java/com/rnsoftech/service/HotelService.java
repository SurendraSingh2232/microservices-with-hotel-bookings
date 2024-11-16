package com.rnsoftech.service;/*
 * @Created 23/04/2024 - 10:46
 * @User ${"PRAVENDRA KUMAR"}
 */

import com.rnsoftech.domain.Hotel;

import java.util.List;

public interface HotelService {

    Hotel createHotel(Hotel hotel);

    List<Hotel> getAll();

    Hotel get(Long hotelId);
}
