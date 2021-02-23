
package com.ReservationSystem;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.ReservationSystem.dao.AgenceDAO;
import com.ReservationSystem.dao.BureauDAO;
import com.ReservationSystem.model.Agence;

@SpringBootTest
class ReservationSystemApplicationTests {

	@Test
	void contextLoads() {
		
		BureauDAO agenceservice=new BureauDAO();
		agenceservice.UpdateBureauAvailability(12, "service chéque et virement");
	}

}

