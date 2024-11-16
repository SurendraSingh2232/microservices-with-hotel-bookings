package com.rnsoftech.service;

import com.rnsoftech.domain.Booking;
import com.rnsoftech.domain.Guest;
import com.rnsoftech.domain.Hotel;
import com.rnsoftech.domain.Room;

import java.time.LocalDate;
import java.util.List;

public interface BookingService {

    Booking createBooking(Guest guest, Room room, Hotel hotel, LocalDate checkInDate, LocalDate checkOutDate);
    Booking getBookingById(Long bookingId);
    List<Booking> getBookingsByGuest(Long guestId);
    boolean checkRoomAvailability(Room room, LocalDate checkInDate, LocalDate checkOutDate);
    void cancelBooking(Long bookingId);
}
