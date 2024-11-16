package com.rnsoftech.service;

import com.rnsoftech.domain.Guest;

import java.util.List;

public interface GuestService {

    Guest createGuest(Guest guest);
    Guest getGuestById(Long id);
    List<Guest> getAllGuests();
    Guest updateGuest(Long id, Guest updatedGuest);
    void deleteGuest(Long id);
}
