package com.ReservationSystem.controllers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import com.ReservationSystem.model.Reservation;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TestReservationController {
    
	
	@Autowired
    private TestRestTemplate testRestTemplate;
	
	//tester la methode getReservationByIdTest() de la classe ReservationController
	@Test
	void getReservationByIdTest() {
		
		 ResponseEntity<Reservation> reservationReturned = testRestTemplate.getForEntity( "/reservation/2", Reservation.class);
		 assertEquals("Hajar",reservationReturned.getBody().getClient().getNom());
		 assertEquals(1,reservationReturned.getBody().getBureau().getBureauId());
		 assertEquals(15978,reservationReturned.getBody().getDuree());
	}
	
}
