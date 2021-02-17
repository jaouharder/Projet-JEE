package com.ReservationSystem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ReservationSystem.configdb.JDBCONFIG;
import com.ReservationSystem.dao.AgenceDAO;
import com.ReservationSystem.dao.BureauDAO;
import com.ReservationSystem.dao.ReservationDAO;
import com.ReservationSystem.model.Agence;
import com.ReservationSystem.model.Bureau;
import com.ReservationSystem.model.Client;
import com.ReservationSystem.model.Reservation;

@SpringBootApplication
public class ReservationSystemApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(ReservationSystemApplication.class, args);
		
		AgenceDAO agence_service=new AgenceDAO();
		List<Agence> agencies= agence_service.findAll(); 
	 
		for (Agence agence : agencies) {
			System.out.println( agence.getBureauList());
		}
	}

}
