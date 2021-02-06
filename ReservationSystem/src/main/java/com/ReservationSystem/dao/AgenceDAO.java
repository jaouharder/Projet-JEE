package com.ReservationSystem.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ReservationSystem.configdb.JDBCONFIG;
import com.ReservationSystem.model.Agence;

public class AgenceDAO {

	Connection cnx=JDBCONFIG.GetConx();
	Statement stat;
	ResultSet result;
	String query;
	
	
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
				 int  disponibilite=result.getInt("disponibilite");
			     Agence agence=new Agence( nom, localisation, disponibilite);
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
				 int  disponibilite=result.getInt("disponibilite");
				 Agence agence=new Agence(nom,localisation,disponibilite);
				 agence.setId(id);
				 
				 return agence;
			 }
			  return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		 
	}
	  
	   //search for an agencies sorted by availability
	    
	   public List<Agence> findbyAvailability(){
		   
		   try {
				 stat=cnx.createStatement();
				 List<Agence> agenceList=new ArrayList<Agence>();
				 query="select * from agence order by disponibilite asc;";
				 result= stat.executeQuery(query);
				while(result.next()) {
				
					 int  id=result.getInt("agence_id"); 
				     String nom=result.getString("agence_name");
					 String localisation=result.getString("localisation");
					 int  disponibilite=result.getInt("disponibilite");
				     Agence agence=new Agence( nom, localisation, disponibilite);
				     agence.setId(id);
				     agenceList.add(agence);
				}
				 return agenceList;
			} catch (SQLException e) {	e.printStackTrace(); return null;	}
		   
	   }
	   
	   //search for agencies sorted by proximity 
	    public List<Agence> findbyProximity(){
	    	  return null;
	    	  /*not implemented yet*/
	    }
	
	
	
	
	
	//insert an agency
	public void createAgency(Agence agence) {
		 try {
			stat=cnx.createStatement();
			query="INSERT INTO AGENCE(agence_name,localisation,disponibilite) VALUES('"+agence.getNom()+"','"+agence.getLocalisation()+"',"+agence.getDisponibilite()+");";
			stat.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	//update availability of an agency
		public void UpdateAvailability(int agence_id,int dispo) {
			 try {
				stat=cnx.createStatement();
				query="UPDATE AGENCE SET DISPONIBILITE="+dispo+"where agence_id="+agence_id+";";
				stat.executeUpdate(query);
			} catch (SQLException e) {
				e.printStackTrace();
			}
	
		}
	
	
	
	
	
	
	
}
