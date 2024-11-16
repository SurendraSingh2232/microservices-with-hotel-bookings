package com.rnsoftech.controller;/*
 * @Created 23/04/2024 - 06:01
 * @User ${"PRAVENDRA KUMAR"}
 */

import com.rnsoftech.domain.User;
import com.rnsoftech.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        log.info("REST Request to createUser: {}", user);
        User result = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable Long userId) {
        log.info("REST Request to getUser: {}", userId);
        User result = userService.getUser(userId);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> result = userService.getAllUsers();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/fetch-all-users-with-ratings-hotels")
    public ResponseEntity<List<User>> fetchAllUsersWithRatingsAndHotels() {
        log.info("REST Request to fetchAllUsersWithRatingsAndHotels: {}");
        List<User> result = userService.getAllUsersWithRatingsAndHotels();
        return ResponseEntity.ok(result);
    }
}
