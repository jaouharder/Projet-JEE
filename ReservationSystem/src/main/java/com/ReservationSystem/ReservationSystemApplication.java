package com.ReservationSystem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ReservationSystem.configdb.JDBCONFIG;
import com.ReservationSystem.dao.ReservationDAO;
import com.ReservationSystem.model.Bureau;
import com.ReservationSystem.model.Client;
import com.ReservationSystem.model.Reservation;

@SpringBootApplication
public class ReservationSystemApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(ReservationSystemApplication.class, args);
		
		
		
	}

}
