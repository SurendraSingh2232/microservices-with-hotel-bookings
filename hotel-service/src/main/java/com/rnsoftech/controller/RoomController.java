package com.rnsoftech.controller;

import com.rnsoftech.domain.Room;
import com.rnsoftech.service.RoomService;
import com.rnsoftech.service.impl.RoomServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    private static final Logger log = LoggerFactory.getLogger(RoomController.class);
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<Room>> getRoomsByHotel(@PathVariable Long hotelId) {
        log.info("REST Request to getRoomsByHotel: {}", hotelId);
        List<Room> rooms = roomService.getRoomsByHotel(hotelId);
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<Room> getRoomById(@PathVariable Long roomId) {
        log.info("REST Request to getRoomById: {}", roomId);
        Room room = roomService.getRoomById(roomId);
        return room != null ? new ResponseEntity<>(room, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Room> createRoom(@RequestBody Room room) {
        log.info("REST Request to createRoom: {}", room);
        Room createdRoom = roomService.createRoom(room);
        return new ResponseEntity<>(createdRoom, HttpStatus.CREATED);
    }

    @PutMapping("/{roomId}")
    public ResponseEntity<Room> updateRoom(@PathVariable Long roomId, @RequestBody Room room) {
        log.info("REST Request to updateRoom: {}, {}", roomId, room);
        Room updatedRoom = roomService.updateRoom(roomId, room);
        return updatedRoom != null ? new ResponseEntity<>(updatedRoom, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{roomId}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long roomId) {
        log.info("REST Request to deleteRoom: {}", roomId);
        boolean deleted = roomService.deleteRoom(roomId);
        return deleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{roomId}/availability")
    public ResponseEntity<Boolean> checkRoomAvailability(@PathVariable Long roomId, @RequestParam LocalDate checkInDate, @RequestParam LocalDate checkOutDate) {
        log.info("REST Request to checkRoomAvailability: {}, {}, {}", roomId, checkInDate, checkOutDate);
        boolean isAvailable = roomService.checkRoomAvailability(roomId, checkInDate, checkOutDate);
        return new ResponseEntity<>(isAvailable, HttpStatus.OK);
    }
}
