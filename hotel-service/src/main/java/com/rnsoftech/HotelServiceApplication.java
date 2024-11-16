package com.rnsoftech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaAuditing
@EnableTransactionManagement
@ComponentScan(basePackages = "com.rnsoftech")
@EntityScan(basePackages = "com.rnsoftech.domain")
@Configuration
@EnableJpaRepositories(basePackages = "com.rnsoftech.repository")
public class HotelServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelServiceApplication.class, args);
		//Swagger URL = http://localhost:8082/swagger-ui/index.html
		//Swagger URL = http://localhost:8082/swagger-ui/index.html

		// Booking Payload
		/*
		{
			"guest": {
			"guestId": 1,
					"name": "Pravendra Kumar",
					"email": "pravendra.kumar@oracle.com",
					"phone": "8095216322",
					"address": "Nagla Bheem"
		},
			"room": {
			"roomId": 1,
					"type": "Single",
					"price": 1500,
					"status": "available",
					"hotel": {
				"hotelId": 1,
						"name": "Rajput Hotel",
						"location": "Kasganj",
						"about": "Excellent"
			}
		},
			"hotel": {
			"hotelId": 1,
					"name": "Rajput Hotel",
					"location": "Kasganj",
					"about": "Excellent"
		},
			"checkInDate": "2024-11-16",
			"checkOutDate": "2024-11-18"
		}
	  */
	}
}
