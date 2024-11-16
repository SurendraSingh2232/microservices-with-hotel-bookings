package com.rnsoftech.service.impl;/*
 * @Created 23/04/2024 - 10:51
 * @User ${"PRAVENDRA KUMAR"}
 */

import com.rnsoftech.domain.Hotel;
import com.rnsoftech.exception.ResourceNotFoundException;
import com.rnsoftech.repository.HotelRepository;
import com.rnsoftech.service.HotelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class HotelServiceImpl implements HotelService {

    private final Logger log = LoggerFactory.getLogger(HotelServiceImpl.class);
    private final HotelRepository hotelRepository;
    public HotelServiceImpl(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public Hotel createHotel(Hotel hotel) {
        log.info("Service Request to createHotel: {}", hotel);
        Hotel result = hotelRepository.save(hotel);
        return result;
    }

    @Override
    public List<Hotel> getAll() {
        log.info("Service Request to getAll: {}");
        List<Hotel> result = hotelRepository.findAll();
        return result;
    }

    @Override
    public Hotel get(Long hotelId) {
        log.info("Service Request to get: {}", hotelId);
        Hotel result = hotelRepository.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException("Hotel with given hotelId not found !!"));
        return result;
    }
}
