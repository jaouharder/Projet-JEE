package com.ReservationSystem;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.ReservationSystem.dao.AgenceDAO;
import com.ReservationSystem.model.Agence;

@SpringBootTest
class ReservationSystemApplicationTests {

	@Test
	void contextLoads() {
		
		// test of agency methods
		AgenceDAO agenceService=new AgenceDAO();
		agenceService.createAgency(new Agence("CIH Paris","Champs Elysee",30));
		System.out.println("agence with id 1 : "+agenceService.findById(1));
		List<Agence> list=agenceService.findbyAvailability();
		System.out.println("agence with order: "+list);
		
	}

}
