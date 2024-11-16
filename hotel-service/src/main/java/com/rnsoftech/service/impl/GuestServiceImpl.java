package com.rnsoftech.service.impl;

import com.rnsoftech.domain.Guest;
import com.rnsoftech.repository.GuestRepository;
import com.rnsoftech.service.GuestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GuestServiceImpl implements GuestService {

    private static final Logger log = LoggerFactory.getLogger(GuestServiceImpl.class);
    private final GuestRepository guestRepository;

    public GuestServiceImpl(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    @Override
    @Transactional
    public Guest createGuest(Guest guest) {
        log.info("Service Request to createGuest: {}", guest);
        return guestRepository.save(guest);
    }

    @Override
    public Guest getGuestById(Long id) {
        log.info("Service Request to getGuestById: {}", id);
        return guestRepository.findById(id).orElse(null);
    }

    @Override
    public List<Guest> getAllGuests() {
        log.info("Service Request to getAllGuests: {}");
        List<Guest> guestList = guestRepository.findAll();
        return guestList;
    }

    @Override
    @Transactional
    public Guest updateGuest(Long id, Guest updatedGuest) {
        log.info("Service Request to updateGuest: {}", updatedGuest);
        Guest guest = guestRepository.findById(id).orElse(null);
        if (guest != null) {
            guest.setName(updatedGuest.getName());
            guest.setEmail(updatedGuest.getEmail());
            guest.setPhone(updatedGuest.getPhone());
            guest.setAddress(updatedGuest.getAddress());
            return guestRepository.save(guest);
        }
        return null;
    }

    @Override
    @Transactional
    public void deleteGuest(Long id) {
        log.info("Service Request to deleteGuest: {}", id);
        guestRepository.deleteById(id);
    }
}
