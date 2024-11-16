package com.rnsoftech.controller;

import com.rnsoftech.domain.Booking;
import com.rnsoftech.domain.Guest;
import com.rnsoftech.domain.Hotel;
import com.rnsoftech.domain.Room;
import com.rnsoftech.request.BookingRequest;
import com.rnsoftech.service.BookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private static final Logger log = LoggerFactory.getLogger(BookingController.class);

    private final BookingService bookingService;
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<Booking> createBooking(
            @RequestBody BookingRequest bookingRequest) {
        log.info("REST Request to createBooking: {}", bookingRequest);
        try {
            Guest guest = bookingRequest.getGuest();
            Room room = bookingRequest.getRoom();
            Hotel hotel = bookingRequest.getHotel();
            LocalDate checkInDate = bookingRequest.getCheckInDate();
            LocalDate checkOutDate = bookingRequest.getCheckOutDate();

            Booking booking = bookingService.createBooking(guest, room, hotel, checkInDate, checkOutDate);
            return new ResponseEntity<>(booking, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Long bookingId) {
        log.info("REST Request to getBookingById: {}", bookingId);
        Booking booking = bookingService.getBookingById(bookingId);
        if (booking != null) {
            return new ResponseEntity<>(booking, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/guest/{guestId}")
    public ResponseEntity<List<Booking>> getBookingsByGuest(@PathVariable Long guestId) {
        log.info("REST Request to getBookingsByGuest: {}", guestId);
        List<Booking> bookings = bookingService.getBookingsByGuest(guestId);
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    @GetMapping("/check-availability")
    public ResponseEntity<Boolean> checkRoomAvailability(
            @RequestParam Long roomId,
            @RequestParam String checkInDate,
            @RequestParam String checkOutDate) {
        log.info("REST Request to checkRoomAvailability: {}, {}, {}", roomId, checkInDate, checkOutDate);

        LocalDate checkIn = LocalDate.parse(checkInDate);
        LocalDate checkOut = LocalDate.parse(checkOutDate);
        Room room = new Room();
        room.setRoomId(roomId);

        boolean available = bookingService.checkRoomAvailability(room, checkIn, checkOut);
        return new ResponseEntity<>(available, HttpStatus.OK);
    }

    @DeleteMapping("/{bookingId}")
    public ResponseEntity<Void> cancelBooking(@PathVariable Long bookingId) {
        log.info("REST Request to cancelBooking: {}", bookingId);
        try {
            bookingService.cancelBooking(bookingId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
