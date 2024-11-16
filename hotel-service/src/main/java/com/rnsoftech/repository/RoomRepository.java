package com.rnsoftech.repository;

import com.rnsoftech.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room> findByStatus(String status);
    //List<Room> findByHotelId(Long hotelId);
    List<Room> findByHotel_HotelId(Long hotelId);
}
