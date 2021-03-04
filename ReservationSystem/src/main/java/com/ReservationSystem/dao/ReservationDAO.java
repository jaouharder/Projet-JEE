package com.ReservationSystem.dao;


import com.ReservationSystem.configdb.JDBCONFIG;
import com.ReservationSystem.model.Agence;
import com.ReservationSystem.model.Bureau;
import com.ReservationSystem.model.Client;
import com.ReservationSystem.model.Reservation;
import com.ReservationSystem.model.ReservationInfo;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ReservationDAO {

    Connection connection = JDBCONFIG.GetConx();
    
    Statement statement;
    ResultSet resultSet;
    String query;
    BureauDAO bureauDAO = new BureauDAO();


    public List<Reservation> findByAgenceId(int id) {
        try {
            statement = connection.createStatement();
            List<Reservation> reservations= new ArrayList<>();
            query = "select * from reservation\n" +
                    "inner join bureau on bureau.bureau_id = reservation.bureau_id\n" +
                    "where agence_id = " + id;
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {

                int reservationId = resultSet.getInt("reservation_id");
                Timestamp horaire = resultSet.getTimestamp("horaire");
                int duree = resultSet.getInt("duree");

                Bureau bureau = bureauDAO.findById(resultSet.getInt("bureau_id"));

                String cin = resultSet.getString("cin_client");
                String prenom = resultSet.getString("prenom_client");
                String nom = resultSet.getString("nom_client");
                String email = resultSet.getString("email_client");

                Client client = new Client(cin, nom, prenom, email);

                Reservation reservation = new Reservation(reservationId, horaire, bureau, client, duree);
                reservations.add(reservation);
            }
            return reservations;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }

    }
    
    
    public List<Reservation> findAll() {

        try {
            statement = connection.createStatement();
            List<Reservation> reservations= new ArrayList<>();
            query = "select * from reservation";
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {

                int reservationId = resultSet.getInt("reservation_id");
                Timestamp horaire = resultSet.getTimestamp("horaire");
                int duree = resultSet.getInt("duree");

                Bureau bureau = bureauDAO.findById(resultSet.getInt("bureau_id"));

                String cin = resultSet.getString("cin_client");
                String prenom = resultSet.getString("prenom_client");
                String nom = resultSet.getString("nom_client");
                String email = resultSet.getString("email_client");
                
                Client client = new Client(cin, nom, prenom, email);

                Reservation reservation = new Reservation(reservationId, horaire, bureau, client, duree);
                reservations.add(reservation);
            }
            return reservations;
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }


    public Reservation findById(int reservationId) {
        try {
            statement = connection.createStatement();
            query = "select * from reservation " +
                    "where reservation_id = " + reservationId + ";";
            resultSet = statement.executeQuery(query);

             if(resultSet.next()) {
                 Timestamp horaire = resultSet.getTimestamp("horaire");
                int duree = resultSet.getInt("duree");

                Bureau bureau = bureauDAO.findById(resultSet.getInt("bureau_id"));

                String cin = resultSet.getString("cin_client");
                String prenom = resultSet.getString("prenom_client");
                String nom = resultSet.getString("nom_client");
                String email = resultSet.getString("email_client");
                
                Client client = new Client(cin, nom, prenom, email);

                return new Reservation(reservationId, horaire, bureau, client, duree);
            }
            else return null;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public int createReservation(Timestamp horaire, int bureauId, String cin, String nom, String prenom, String email) {
        try {
            statement = connection.createStatement();

            int duree = 0;

            query ="INSERT INTO reservation(cin_client, nom_client, prenom_client, email_client, horaire, duree, bureau_id) " +
                    "VALUES('" + cin + "', '" + nom + "', '" + prenom +
                    "', '" + email + "', '" + horaire + "', " + duree + ", " + bureauId + ");";

            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet generatedKeys = statement.getGeneratedKeys();
            generatedKeys.next();
            //System.out.println(generatedKeys.getString(1));
            return Integer.parseInt(generatedKeys.getString(1));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }
    }


    //Updates "horaire" & "bureau_id"
    public void updateReservation(int reservationId, @NotNull Timestamp horaire, int bureauId) {
        try {
            statement = connection.createStatement();

            query = "UPDATE RESERVATION " +
                    "SET HORAIRE = '" + horaire + "', " +
                    "BUREAU_ID = " + bureauId +
                    " WHERE RESERVATION_ID = " + reservationId + ";";

            statement.executeUpdate(query);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteReservation(int reservationId) {
        try{
            statement = connection.createStatement();

            query = "DELETE FROM RESERVATION " +
                    "WHERE RESERVATION_ID = " + reservationId + ";";

            statement.executeUpdate(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    
	
    
    public void sendEmailVerification(ReservationInfo reservation, JavaMailSender javamailsender) throws SQLException {
		Bureau br= bureauDAO.findById(reservation.getBureauId());
		Agence ag = br.getAgence();
    	SimpleMailMessage mail=new SimpleMailMessage();
		mail.setTo(reservation.getEmail());
		mail.setFrom("oninebankensias@gmail.com");
		mail.setSubject("Bank Reservation");
		mail.setText("Bonjour Mr "+reservation.getNom()+".\nNous vous confirmons que vous avez bien résérevé votre place á  l'agence "+ag.getNom()+" á  l'heure: "+reservation.getHoraire()+"\nVoici votre clé de reservation : "+reservation.getReservationId() +".\nCordialement.");
		javamailsender.send(mail);
		 System.out.println("Mail sent ");
    }
    
    


    public List<Reservation> findByBureauId(int bureauId) {
        try {
            List<Reservation> reservations= new ArrayList<>();
            statement = connection.createStatement();
            query = "select * from reservation " +
                    "where bureau_id = " + bureauId + ";";
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {

                int reservationId = resultSet.getInt("reservation_id");
                Timestamp horaire = resultSet.getTimestamp("horaire");
                int duree = resultSet.getInt("duree");

                Bureau bureau = bureauDAO.findById(resultSet.getInt("bureau_id"));


                String cin = resultSet.getString("cin_client");
                String prenom = resultSet.getString("prenom_client");
                String nom = resultSet.getString("nom_client");
                String email = resultSet.getString("email_client");

                Client client = new Client(cin, nom, prenom, email);

                Reservation reservation = new Reservation(reservationId, horaire, bureau, client, duree);
                reservations.add(reservation);
            }
            return reservations;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }
    
    public void setDuree(int reservationId, int duree) {
        try {
            statement = connection.createStatement();

            query = "UPDATE RESERVATION " +
                    "SET DUREE = '" + duree + "' "+
                    "WHERE RESERVATION_ID = " + reservationId + ";";

            statement.executeUpdate(query);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    
    
    
    
    public int GetBuIdByReservationId(int reservationid) {
    	
   	 try {
   		 int BurId=0;
			statement = connection.createStatement();
			query = "select bureau_id from reservation " +
	                 "where reservation_Id = " + reservationid + ";";
	         resultSet = statement.executeQuery(query);
	         while (resultSet.next()) { BurId = resultSet.getInt("bureau_id");}
	         return BurId;
	    
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
   }
}
