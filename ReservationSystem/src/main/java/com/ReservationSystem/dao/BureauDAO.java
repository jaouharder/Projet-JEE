package com.ReservationSystem.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ReservationSystem.configdb.JDBCONFIG;
import com.ReservationSystem.model.Agence;
import com.ReservationSystem.model.Bureau;


@Component
public class BureauDAO {
	static Connection conx=JDBCONFIG.GetConx();
	
	
	
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
  
    public String GetServiceById(int bureauId) {
    	 try {
    		 String output="";
			Statement stat=conx.createStatement();
			String query="select service from bureau where bureau_Id="+bureauId+";";
			ResultSet service=stat.executeQuery(query);
			while(service.next()) {
				output=service.getString("service");
			}
			return output;
		} catch (SQLException e) { e.printStackTrace(); return null;}
    	
    	
    }
	
	
	
	
	
	
	public void calculTaux(int bureauId) {
	       Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	       System.out.println(timestamp);
	       timestamp = nearestOpenTime(timestamp);
	       Timestamp timestamp2 = nearestClosedTime(timestamp);
	       
	       int reservationsLeft = calculReservationsLeft(timestamp);
	       
	       Statement stat;int output=0;
	       try {
				stat = conx.createStatement();
				
				String query="select count(*) as nbr from reservation  where bureau_id="+bureauId+" and horaire> '"+timestamp+"' and horaire< '"+timestamp2+"';";
				ResultSet result=stat.executeQuery(query);
				while(result.next()) {
					output=result.getInt("nbr");
				}
				
				
				
				double taux=1-((float) output/ (float)reservationsLeft);
				taux=100*taux;System.out.println(output +"   as     " +reservationsLeft+" nearest    "+timestamp);
				System.out.println(taux);
			    
				 stat=conx.createStatement();
				 query="UPDATE BUREAU SET bureau_availability="+taux+" where bureau_Id="+bureauId+";";
				System.out.println( stat.executeUpdate(query));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

	   }

	public Timestamp nearestOpenTime(Timestamp timestamp) {
     Timestamp timestamp1 = new Timestamp(timestamp.getTime());
     Calendar c= Calendar.getInstance();
     c.setTimeInMillis(timestamp1.getTime());
     if (c.get(Calendar.HOUR_OF_DAY) >= 18 || (c.get(Calendar.HOUR_OF_DAY) == 17 && c.get(Calendar.MINUTE) >= 30)) {
         c.set(Calendar.HOUR_OF_DAY, 8);
	       c.set(Calendar.MINUTE, 0);
	       c.set(Calendar.SECOND, 0);
	       c.set(Calendar.MILLISECOND, 0);
         
	       c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH)+1);
     }
     else if (c.get(Calendar.HOUR_OF_DAY) < 8) {
         c.set(Calendar.HOUR_OF_DAY, 8);
	       c.set(Calendar.MINUTE, 0);
	       c.set(Calendar.SECOND, 0);
	       c.set(Calendar.MILLISECOND, 0);
     }
     else if (c.get(Calendar.HOUR_OF_DAY) < 14 && c.get(Calendar.HOUR_OF_DAY) >= 12) {
         c.set(Calendar.HOUR_OF_DAY, 14);
     }
     timestamp1.setTime(c.getTimeInMillis());
     return timestamp1;
 }

 public Timestamp nearestClosedTime(Timestamp timestamp) {
     Timestamp timestamp1 = new Timestamp(timestamp.getTime());
     Calendar c = Calendar.getInstance();
     c.setTimeInMillis(timestamp.getTime());
	       c.set(Calendar.MINUTE, 0);
	       c.set(Calendar.SECOND, 0);
	       c.set(Calendar.MILLISECOND, 0);
     c.set(Calendar.HOUR_OF_DAY, 18);
     timestamp1.setTime(c.getTimeInMillis());
     return timestamp1;
 }

 public int calculReservationsLeft(Timestamp timestamp) {

     Timestamp dayEnd = new Timestamp(timestamp.getTime());

     Calendar c= Calendar.getInstance();
     
     c.setTimeInMillis(timestamp.getTime());
     boolean isMorning = (c.get(Calendar.HOUR_OF_DAY) < 14);
     
     c.setTimeInMillis(dayEnd.getTime());
     c.set(Calendar.HOUR_OF_DAY, 18);
     c.set(Calendar.MINUTE, 0);
     c.set(Calendar.SECOND, 0);
     c.set(Calendar.MILLISECOND, 0);

     dayEnd.setTime(c.getTimeInMillis());

     Timestamp timeLeft = new Timestamp( dayEnd.getTime() - timestamp.getTime());

     c.setTimeInMillis(timeLeft.getTime());

     int minutesLeft = c.get(Calendar.HOUR_OF_DAY)*60 + c.get(Calendar.MINUTE);
     if (isMorning) minutesLeft -= 120;
     return minutesLeft/30;
 }

}



