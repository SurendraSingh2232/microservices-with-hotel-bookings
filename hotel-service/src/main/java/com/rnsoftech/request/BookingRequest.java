package com.rnsoftech.request;

import com.rnsoftech.domain.Guest;
import com.rnsoftech.domain.Hotel;
import com.rnsoftech.domain.Room;

import java.time.LocalDate;

public class BookingRequest {

    private Guest guest;
    private Room room;
    private Hotel hotel;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    @Override
    public String toString() {
        return "BookingRequest{" +
                "guest=" + guest +
                ", room=" + room +
                ", hotel=" + hotel +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                '}';
    }
}
