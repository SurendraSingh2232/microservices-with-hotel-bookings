package com.rnsoftech.controller;/*
 * @Created 23/04/2024 - 11:00
 * @User ${"PRAVENDRA KUMAR"}
 */

import com.rnsoftech.domain.Hotel;
import com.rnsoftech.service.HotelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    private final Logger log = LoggerFactory.getLogger(HotelController.class);
    private final HotelService hotelService;
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping
    public ResponseEntity<Hotel> saveHotel(@RequestBody Hotel hotel) {
        log.info("REST Request to saveHotel: {}", hotel);
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.createHotel(hotel));
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getHotel(@PathVariable Long hotelId) {
        log.info("REST Request to getHotel: {}", hotelId);
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.get(hotelId));
    }

    @GetMapping
    public ResponseEntity<List<Hotel>> findAllHotel() {
        log.info("REST Request to findAllHotel: {}");
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.getAll());
    }
}
