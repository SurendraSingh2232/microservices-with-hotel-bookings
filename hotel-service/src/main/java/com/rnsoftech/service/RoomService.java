package com.rnsoftech.service;

import com.rnsoftech.domain.Room;

import java.time.LocalDate;
import java.util.List;

public interface RoomService {

    List<Room> getRoomsByHotel(Long hotelId);
    Room getRoomById(Long roomId);
    Room createRoom(Room room);
    Room updateRoom(Long roomId, Room room);
    boolean deleteRoom(Long roomId);
    boolean checkRoomAvailability(Long roomId, LocalDate checkInDate, LocalDate checkOutDate);
}
