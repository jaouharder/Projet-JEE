/*****
By DERROUICH JAOUHAR 
ENSIAS-GL1
*****/

package com.ReservationSystem.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ReservationSystem.configdb.JDBCONFIG;
import com.ReservationSystem.model.Agence;
import com.ReservationSystem.model.Bureau;

@SpringBootApplication
public class BureauDAO {
	static Connection conx=JDBCONFIG.GetConx();
	
	
	public static void getBureaux(int agenceId) {
		//SpringApplication.run(BureauDAO.class, args);	
		
		/*try {
			
			Statement stat=conx.createStatement();
			ResultSet resultagc=stat.executeQuery("select * from agence where agence_Id="+agenceId+";");
			resultagc.next();
			String agname=resultagc.getString("agence_name");
			String localisation=resultagc.getString("localisation");
			System.out.println("L'agence  : "+agname+"\nlocalisation : "+localisation+"\nIl y a les sevices suivant: ");
			ResultSet result= stat.executeQuery("select * from bureau where agence_Id="+agenceId+";");
			while(result.next()) {
				 String srv=result.getString("service");
				 int  disponibilite=result.getInt("bureau_disponibilite");
				 System.out.println("Service : "+srv+"   -----   disponibilte : "+disponibilite+" %");			 
			}
		} catch (SQLException e) {e.printStackTrace();}
		*/
	}
public static void main(String[] args) throws SQLException {
	SpringApplication.run(BureauDAO.class, args);
	findById(1);
	}
public static Bureau findById(int bureauId) throws SQLException {
	
	Statement stat=conx.createStatement();
	ResultSet resultagc=stat.executeQuery("select * from bureau where bureau_Id="+bureauId+";");
	resultagc.next();
	int id = resultagc.getInt("bureau_Id");
	String service=resultagc.getString("service");
	int dispo=resultagc.getInt("bureau_disponibilite");
	Agence agc= AgenceDAO.getAgncebyId(resultagc.getInt("agence_Id")); 
	Bureau br = new Bureau(id,service,dispo,agc);
	return br;
}
public static String authentification(String username,String password,int bureauId) throws SQLException {
	Statement stat=conx.createStatement();
	ResultSet result=stat.executeQuery("select * from employee where username="+username+";");
	result.next();
	if(password.equals(result.getString("password"))) {
		ResultSet resultagc=stat.executeQuery("UPDATE employee SET bureau_Id="+bureauId+" WHERE username="+username+";");
		return "ACCESS GAINED";
	}
	else {
		return "INCORRECT PASSWORD";
	}
}

}

