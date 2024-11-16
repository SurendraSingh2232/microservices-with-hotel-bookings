package com.rnsoftech.service.impl;

import com.rnsoftech.domain.Booking;
import com.rnsoftech.domain.Room;
import com.rnsoftech.repository.BookingRepository;
import com.rnsoftech.repository.RoomRepository;
import com.rnsoftech.service.RoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {

    private static final Logger log = LoggerFactory.getLogger(RoomServiceImpl.class);

    private final RoomRepository roomRepository;
    private final BookingRepository bookingRepository;

    public RoomServiceImpl(RoomRepository roomRepository, BookingRepository bookingRepository) {
        this.roomRepository = roomRepository;
        this.bookingRepository = bookingRepository;
    }


    @Override
    public List<Room> getRoomsByHotel(Long hotelId) {
        log.info("Service Request to getRoomsByHotel: {}", hotelId);
        return roomRepository.findByHotel_HotelId(hotelId);
    }

    @Override
    public Room getRoomById(Long roomId) {
        log.info("Service Request to getRoomById: {}", roomId);
        Optional<Room> room = roomRepository.findById(roomId);
        return room.orElse(null);
    }

    @Override
    public Room createRoom(Room room) {
        log.info("Service Request to createRoom: {}", room);
        return roomRepository.save(room);
    }

    @Override
    public Room updateRoom(Long roomId, Room room) {
        log.info("Service Request to updateRoom: {}, {}", roomId, room);
        Optional<Room> existingRoom = roomRepository.findById(roomId);
        if (existingRoom.isPresent()) {
            Room updatedRoom = existingRoom.get();
            updatedRoom.setType(room.getType());
            updatedRoom.setPrice(room.getPrice());
            updatedRoom.setStatus(room.getStatus());
            updatedRoom.setHotel(room.getHotel());
            return roomRepository.save(updatedRoom);
        }
        return null;
    }

    @Override
    public boolean deleteRoom(Long roomId) {
        log.info("Service Request to deleteRoom: {}", roomId);
        if (roomRepository.existsById(roomId)) {
            roomRepository.deleteById(roomId);
            return true;
        }
        return false;
    }

    @Override
    public boolean checkRoomAvailability(Long roomId, LocalDate checkInDate, LocalDate checkOutDate) {
        log.info("Service Request to checkRoomAvailability: {}", roomId);
        List<Booking> bookingList = bookingRepository.findByRoom_RoomIdAndCheckInDateLessThanEqualAndCheckOutDateGreaterThanEqual(roomId, checkInDate, checkOutDate);
        return bookingList.isEmpty();
    }
}
