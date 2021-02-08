package com.ReservationSystem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ReservationSystem.configdb.JDBCONFIG;

@SpringBootApplication
public class ReservationSystemApplication implements CommandLineRunner{

	
	public static void main(String[] args) {
		SpringApplication.run(ReservationSystemApplication.class, args);	
		
	}

	@Override
	public void run(String... args) throws Exception {
		/*le code qui va être exécuté ne doit pas être dans la fonction main, doit être dans cette methode*/
		
		  try {
			Connection conx=JDBCONFIG.GetConx();
			Statement stat=conx.createStatement();
			ResultSet result= stat.executeQuery("select * from agence;");
			while(result.next()) {
				 String address=result.getString("agence_name");
				 String localisation=result.getString("localisation"); 
				 int  disponibilite=result.getInt("disponibilite");
				 System.out.println("addresse:"+address+",localisation :"+localisation+",disponobilte % :"+disponibilite);
			
			}
		  } catch (SQLException e) {e.printStackTrace();}
		
		
	}

}
