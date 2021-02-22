package com.ReservationSystem.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ReservationSystem.configdb.JDBCONFIG;
import com.ReservationSystem.model.Agence;
import com.ReservationSystem.model.Bureau;

@Component
public class AgenceDAO {

	private Connection cnx=JDBCONFIG.GetConx();
 
	private Statement stat;
	private ResultSet result;
	private String query;
	
	
	//select all agencies
	public List<Agence> findAll() {
		
		try {
			 stat=cnx.createStatement();
			 List<Agence> agenceList=new ArrayList<Agence>();
			 query="select * from agence;";
			 result= stat.executeQuery(query);
			while(result.next()) {
			
				 int  id=result.getInt("agence_id"); 
			     String nom=result.getString("agence_name");
				 String localisation=result.getString("localisation");
				 float latitude=result.getFloat("latitude");
				 float longitude=result.getFloat("longitude");
				 BureauDAO bureau_service=new BureauDAO();
				 List<Bureau> bureauList= bureau_service.getBureauxByAgId(id);
				 
				 Agence agence=new Agence(nom,localisation,latitude,longitude,bureauList);
			     agence.setId(id);
			     agenceList.add(agence);
			}
			 return agenceList;
		} catch (SQLException e) {	e.printStackTrace(); return null;	}
		
		
	}
	//select an agency by id
	public Agence findById(int agence_id) {
		 
		 try {
			 stat=cnx.createStatement();
			 query="select * from agence where agence_id="+agence_id+";";
			 result= stat.executeQuery(query);
			 if(result.next()) {
				 int  id=result.getInt("agence_id"); 
			     String nom=result.getString("agence_name");
				 String localisation=result.getString("localisation");
				 float latitude=result.getFloat("latitude");
				 float longitude=result.getFloat("latitude");
				 BureauDAO bureau_service=new BureauDAO();
				 List<Bureau> bureauList= bureau_service.getBureauxByAgId(id);
				 Agence agence=new Agence(nom,localisation,latitude,longitude,bureauList);
				 agence.setId(id);		 
				 return agence;
			 }
			  return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		 
	}
	  
	  
	
	
	
	
	
	//insert an agency
	public void createAgency(Agence agence) {
		 try {
			stat=cnx.createStatement();
			query="INSERT INTO AGENCE(agence_name,localisation,latitude,longitude) VALUES('"+agence.getNom()+"','"+agence.getLocalisation()+"',"+agence.getLatitude()+","+agence.getLongitude()+");";
			stat.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public boolean employeExists(int bureauId, String password) {
		try {
			stat = cnx.createStatement();
			query = "select * from employe " +
					"where bureau_id = " + bureauId + " AND password = \"" + password + "\";";
			result = stat.executeQuery(query);
			return result.next();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return false;
	}
	
	
	
	
	
	
	
}
