package com.ReservationSystem.configdb;



import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class JDBCONFIG {

	
	   private static JDBCONFIG config=new JDBCONFIG();
	   Properties prop=new Properties();
	   private static Connection cnx;
	   
	   
	   private JDBCONFIG() { 
		   try {
			   InputStream in=Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties");
			   prop.load(in);
			   Class.forName(prop.getProperty("Jdbc.Driver"));
		   }catch(Exception e) {
			   e.printStackTrace();
		   }
	   }
	   
	   private Connection CreateConx() {
		   try {
			   String DB_URL=prop.getProperty("DB.URL");
			   String User=prop.getProperty("User");
			   String Password=prop.getProperty("Password");
			   cnx=DriverManager.getConnection(DB_URL,User,Password);
		   }catch(Exception e) {
			   e.printStackTrace();
		   }
		   return cnx;
	   }
	   
	   public static Connection GetConx() {
		   if(cnx==null) {
			   cnx=config.CreateConx();
		   }
			  return cnx;
	   }
}
