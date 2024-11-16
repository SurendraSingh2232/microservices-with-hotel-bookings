package com.rnsoftech.repository;

import com.rnsoftech.domain.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    //List<Booking> findByGuestId(Long guestId);
    List<Booking> findByGuest_GuestId(Long guestId);
    List<Booking> findByRoom_RoomIdAndCheckInDateLessThanEqualAndCheckOutDateGreaterThanEqual(
            Long roomId, LocalDate checkInDate, LocalDate checkOutDate);
}
