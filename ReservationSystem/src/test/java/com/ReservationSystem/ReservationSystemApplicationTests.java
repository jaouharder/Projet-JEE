
  package com.ReservationSystem;
  
  import org.junit.jupiter.api.Test; 
  import org.springframework.boot.test.context.SpringBootTest;
  
  import com.ReservationSystem.dao.AgenceDAO; 
  import com.ReservationSystem.dao.BureauDAO; 
  import com.ReservationSystem.model.Agence;
  
  
  @SpringBootTest class ReservationSystemApplicationTests {
  
  @Test void contextLoads() {
  
		
				 BureauDAO agenceservice=new BureauDAO();
				 agenceservice.UpdateBureauAvailability(12, "service chéque et virement");
		 
        } 
  
  }
  
 