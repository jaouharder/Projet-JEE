package com.ReservationSystem.dao;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.ReservationSystem.model.Bureau;
import com.ReservationSystem.model.Client;
import com.ReservationSystem.model.Reservation;

class TestReservationDao {
    
	//tester la methode findById() de la classe RservationDAO
	@Test
	void findByIdTest() {
		ReservationDAO reservationdao = new ReservationDAO();
		Client client = new Client("M222222","Benettaleb","Amine","benettaleb.amine1999@gmail.com");
		Bureau bureau = new Bureau(1,"schèque",30,true);
		Reservation reservationExpected = new Reservation(19, java.sql.Timestamp.valueOf("2021-02-22 11:30:00"), bureau, client, 0 );
		Reservation reservationReturned = reservationdao.findById(19);
		assertEquals(reservationExpected.getReservationId(),reservationReturned.getReservationId());
		
		
	}

}
