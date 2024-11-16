package com.rnsoftech;

import com.rnsoftech.domain.Rating;
import com.rnsoftech.external.services.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class UserServiceApplicationTests {

    @Test
	void contextLoads() {
	}

	@Autowired
	private RatingService ratingService;

//	@Test
//	void createRating() {
//		Rating rating = Rating.builder().rating(10).userId(2L).hotelId(5L).feedback("this is created using feign client").build();
//		ResponseEntity<Rating> ratingResponseEntity = ratingService.createRating(rating);
//		System.out.println("New rating created" + ratingResponseEntity);
//	}
}
