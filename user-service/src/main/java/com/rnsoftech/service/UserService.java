package com.rnsoftech.service;/*
 * @Created 22/04/2024 - 22:08
 * @User ${"PRAVENDRA KUMAR"}
 */

import com.rnsoftech.domain.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);
    List<User> getAllUsers();
    User getUser(Long userId);
    List<User> getAllUsersWithRatingsAndHotels();
}
