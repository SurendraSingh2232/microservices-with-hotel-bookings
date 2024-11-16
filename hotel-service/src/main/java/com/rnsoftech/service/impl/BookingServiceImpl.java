package com.rnsoftech.service.impl;

import com.rnsoftech.domain.Booking;
import com.rnsoftech.domain.Guest;
import com.rnsoftech.domain.Hotel;
import com.rnsoftech.domain.Room;
import com.rnsoftech.repository.BookingRepository;
import com.rnsoftech.repository.GuestRepository;
import com.rnsoftech.repository.HotelRepository;
import com.rnsoftech.repository.RoomRepository;
import com.rnsoftech.service.BookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    private static final Logger log = LoggerFactory.getLogger(BookingServiceImpl.class);

    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;
    private final HotelRepository hotelRepository;

    public BookingServiceImpl(BookingRepository bookingRepository, RoomRepository roomRepository, GuestRepository guestRepository, HotelRepository hotelRepository) {
        this.bookingRepository = bookingRepository;
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.hotelRepository = hotelRepository;
    }

    @Override
    @Transactional
    public Booking createBooking(Guest guest, Room room, Hotel hotel, LocalDate checkInDate, LocalDate checkOutDate) {
        log.info("REST Request to createBooking: {}", room);

        if (!checkRoomAvailability(room, checkInDate, checkOutDate)) {
            throw new IllegalArgumentException("The room is not available for the selected dates.");
        }
        Optional<Guest> existingGuest = guestRepository.findById(guest.getGuestId());
        if (!existingGuest.isPresent()) {
            throw new IllegalArgumentException("Guest not found.");
        }
        Optional<Hotel> existingHotel = hotelRepository.findById(hotel.getHotelId());
        if (!existingHotel.isPresent()) {
            throw new IllegalArgumentException("Hotel not found.");
        }
        if (room.getStatus().equals("occupied")) {
            throw new IllegalArgumentException("Room is already occupied.");
        }
        Booking booking = new Booking(guest, room, hotel, checkInDate, checkOutDate);
        booking.setStatus("confirmed");

        room.setStatus("occupied");
        roomRepository.save(room);
        return bookingRepository.save(booking);
    }

    @Override
    public Booking getBookingById(Long bookingId) {
        log.info("Service Request to getBookingById: {}", bookingId);
        Optional<Booking> booking = bookingRepository.findById(bookingId);
        return booking.orElse(null);
    }

    @Override
    public List<Booking> getBookingsByGuest(Long guestId) {
        log.info("Service Request to getBookingsByGuest: {}", guestId);
        return bookingRepository.findByGuest_GuestId(guestId);
    }

    @Override
    public boolean checkRoomAvailability(Room room, LocalDate checkInDate, LocalDate checkOutDate) {
        log.info("Service Request to checkRoomAvailability: {}", room);
        List<Booking> bookings = bookingRepository.findByRoom_RoomIdAndCheckInDateLessThanEqualAndCheckOutDateGreaterThanEqual(room.getRoomId(), checkOutDate, checkInDate);
        return bookings.isEmpty();
    }

    @Override
    @Transactional
    public void cancelBooking(Long bookingId) {
        log.info("Service Request to cancelBooking: {}", bookingId);
        Optional<Booking> bookingOpt = bookingRepository.findById(bookingId);
        if (bookingOpt.isPresent()) {
            throw new IllegalArgumentException("Booking not found.");
        }

        Booking booking = bookingOpt.get();
        Room room = booking.getRoom();
        room.setStatus("available");
        roomRepository.save(room);

        booking.setStatus("canceled");
        bookingRepository.save(booking);
    }
}
