/*****
By DERROUICH JAOUHAR 
ENSIAS-GL1
*****/

package com.ReservationSystem.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import com.ReservationSystem.configdb.JDBCONFIG;
import com.ReservationSystem.model.Agence;
import com.ReservationSystem.model.Bureau;
import com.ReservationSystem.model.Reservation;

@SpringBootApplication
public class BureauDAO {
	static Connection conx=JDBCONFIG.GetConx();
	
	
	public static List<Bureau> getBureauxByAvb(String service,String city) {		
		  try {
		  
		  Statement stat=conx.createStatement(); 
		  List<Bureau> bureaux= new ArrayList<Bureau>();
		  String query="SELECT * FROM agence AS ag JOIN bureau AS br ON ag.agence_Id=br.agence_Id WHERE ag.city='"+city+"' AND br.isavailable=1 AND br.service='"+service+"';";
		  ResultSet resultagc=stat.executeQuery(query);		  
		//  if (!resultagc.next()) { System.err.println("NO AGENCE AVAILABLE IN THIS CITY WITH THIS SERVICE ");} 
		 // else {
		  while(resultagc.next()) {
			      int avb=resultagc.getInt("bureau_availability");
			      String agname=resultagc.getString("agence_name");
			      String local=resultagc.getString("localisation");
			      Agence agc= new Agence(agname, local);
				  Bureau br = new Bureau(service,agc,avb);
				  bureaux.add(br);
			     } 
		  System.out.println(bureaux.toString());
			     Collections.sort(bureaux,new Comparator<Bureau>() {

					@Override
					public int compare(Bureau o1, Bureau o2) {
						int ds2=o2.getBureau_disp(),ds1=o1.getBureau_disp();
						if(ds1>ds2) return 1;
						else if (ds1==ds2) return 0;
						else return -1;
					}
				});
		  return bureaux;  
		  //  }
		  }catch (SQLException e) {e.printStackTrace();}
		return null;
		 	
	}
public static void main(String[] args) throws SQLException {
	SpringApplication.run(BureauDAO.class, args);
	System.out.println(getBureauxByAvb("CREATION DE COMPTE","meknes").toString());
	//getBureaux(8);
//	System.out.println(findById(2).toString());
	}
public  Bureau findById(int bureauId) throws SQLException {
	
	Statement stat=conx.createStatement();
	ResultSet resultagc=stat.executeQuery("select * from bureau where bureau_Id="+bureauId+";");
	if(!resultagc.next()) {
		System.err.println("NO BUREAU WITH ID = "+bureauId);
		return null;
	}
	else {
	int id = resultagc.getInt("bureau_Id");
	String service=resultagc.getString("service");
	int dispo=resultagc.getInt("bureau_availability");
	boolean disp = resultagc.getBoolean("isavailable");
	AgenceDAO agcDao = new AgenceDAO();
	Agence agc= agcDao.findById(resultagc.getInt("agence_Id")); 
	Bureau br = new Bureau(id,service,dispo,agc, disp);
	return br;
	}
}
public  String authentification(String username,String password,int bureauId) throws SQLException {
	Statement stat=conx.createStatement();
	ResultSet result=stat.executeQuery("select * from employee where username="+username+";");
	
	if(!result.next()) return "INVALID USERNAME";
	else if(password.equals(result.getString("password"))) {
		stat.executeUpdate("UPDATE employee SET bureau_Id="+bureauId+" WHERE username="+username+";");
		return "ACCESS GAINED";
	}
	    else {
		     return "INCORRECT PASSWORD";
	}
	}





public  List<Bureau> getBureauxByAgId(int AgId) { 
	  try {
	  Statement stat=conx.createStatement(); 
	  List<Bureau> bureaux= new ArrayList<Bureau>();
	  String query="SELECT * FROM bureau WHERE agence_Id="+AgId+";";
	  ResultSet resultagc=stat.executeQuery(query);
	  while(resultagc.next()) {
	  int id = resultagc.getInt("bureau_Id");
	     int avb=resultagc.getInt("bureau_availability");
	     String service=resultagc.getString("service");
	     Bureau br = new Bureau(id,service,avb,true);
	     bureaux.add(br);
	     }
	  return bureaux;
	  
	  }catch (SQLException e) {e.printStackTrace();}
	return null;
	}













}



