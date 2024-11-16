package com.rnsoftech.service.impl;/*
 * @Created 22/04/2024 - 22:09
 * @User ${"PRAVENDRA KUMAR"}
 */

import com.rnsoftech.domain.Hotel;
import com.rnsoftech.domain.Rating;
import com.rnsoftech.domain.User;
import com.rnsoftech.exception.ResourceNotFoundException;
import com.rnsoftech.external.services.HotelService;
import com.rnsoftech.repository.UserRepository;
import com.rnsoftech.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private final RestTemplate restTemplate;
    private final HotelService hotelService;
    public UserServiceImpl(UserRepository userRepository, RestTemplate restTemplate, HotelService hotelService) {
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
        this.hotelService = hotelService;
    }

    @Override
    public User saveUser(User user) {
        log.info("Service Request to saveUser: {}", user);
        User result = userRepository.save(user);
        return result;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> result = userRepository.findAll();
        return result;
    }

    @Override
    public User getUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server !! : " + userId));
        // fetch rating of the above user from RATING SERVICE
        Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(), Rating[].class);
        log.info("Service Request to call RATING SERVICE to fetch rating of User: {}", ratingsOfUser);

        List<Rating> ratings = Arrays.stream(ratingsOfUser).collect(Collectors.toList());
        List<Rating> ratingList = ratings.stream().map(rating -> {
            // api call to hotel service to get the hotel
            // set the hotel to rating
            // return the rating
            // http://localhost:8082/hotels/1

            //ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class);
            //Hotel hotel = forEntity.getBody();
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            log.info("HOTEL DETAILS FETCHED VIA FEIGN CLIENT: {}", hotel);
            rating.setHotel(hotel);
            rating.setHotelId(hotel.getHotelId());
            return rating;
        }).collect(Collectors.toList());
        user.setRatings(ratingList);
        return user;
    }

    @Override
    public List<User> getAllUsersWithRatingsAndHotels() {
        log.info("Service Request to getAllUsersWithRatingsAndHotels: {}");
        List<User> userList = userRepository.findAll();

        return userList.stream().map(user -> {
            Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(), Rating[].class);
            log.info("Service Request to call RATING SERVICE to fetch ratings for User: " + user.getUserId());

            List<Rating> ratings = Arrays.stream(ratingsOfUser).collect(Collectors.toList());
            List<Hotel> allHotels  = hotelService.getAllHotels();
            log.info("Fetched all hotel details from HOTEL-SERVICE.");

            List<Rating> listOfRatings = ratings.stream().map(rating -> {
                Hotel hotel = allHotels.stream()
                        .filter(h -> h.getHotelId().equals(rating.getHotelId()))
                        .findFirst()
                        .orElse(null);

                if (hotel != null) {
                    log.info("List of rating with hotel: " + hotel);
                    rating.setHotel(hotel);
                } else {
                    log.warn("No hotel found for rating with hotelId: " + rating.getHotelId());
                }
                rating.setHotelId(rating.getHotelId());

                return rating;
            }).collect(Collectors.toList());

            user.setRatings(listOfRatings);
            return user;
        }).collect(Collectors.toList());
    }
}
