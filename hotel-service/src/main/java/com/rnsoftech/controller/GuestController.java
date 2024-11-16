package com.rnsoftech.controller;

import com.rnsoftech.domain.Guest;
import com.rnsoftech.service.GuestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/guests")
public class GuestController {

    private static final Logger log = LoggerFactory.getLogger(GuestController.class);
    private final GuestService guestService;

    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }


    @PostMapping
    public ResponseEntity<Guest> createGuest(@RequestBody Guest guest) {
        log.info("REST Request to createGuest: {}", guest);
        Guest createdGuest = guestService.createGuest(guest);
        return new ResponseEntity<>(createdGuest, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Guest> getGuestById(@PathVariable Long id) {
        log.info("REST Request to getGuestById: {}", id);
        Guest guest = guestService.getGuestById(id);
        return guest != null ? new ResponseEntity<>(guest, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<Guest>> getAllGuests() {
        log.info("REST Request to getAllGuests: {}");
        List<Guest> guests = guestService.getAllGuests();
        return new ResponseEntity<>(guests, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Guest> updateGuest(@PathVariable Long id, @RequestBody Guest guest) {
        log.info("REST Request to updateGuest: {}, {}", id, guest);
        Guest updatedGuest = guestService.updateGuest(id, guest);
        return updatedGuest != null ? new ResponseEntity<>(updatedGuest, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGuest(@PathVariable Long id) {
        log.info("REST Request to deleteGuest: {}", id);
        guestService.deleteGuest(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
