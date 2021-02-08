package com.ReservationSystem.dao;


import com.ReservationSystem.configdb.JDBCONFIG;
import com.ReservationSystem.model.Bureau;
import com.ReservationSystem.model.Client;
import com.ReservationSystem.model.Reservation;


import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ReservationDAO {

    Connection connection = JDBCONFIG.GetConx();
    
    Statement statement;
    ResultSet resultSet;
    String query;
    
    
    
    
    public List<Reservation> findAll() {

        try {
            statement = connection.createStatement();
            List<Reservation> reservations= new ArrayList<Reservation>();
            query = "select * from reservation";
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {

                int reservationId = resultSet.getInt("reservation_id");
                Time horaire = resultSet.getTime("horaire");
                int duree = resultSet.getInt("duree");

                //TODO Adapt to class Bureau
                Bureau bureau = BureauDAO.findById(resultSet.getInt("bureau_id"));

                //TODO Choose the correct method between the 2 following:

                //Method #1
                //Client client = Client.findById(resultSet.getString("cin_client"));

                //Method #2
                //type of cin is int in class Client, should be String for example AB12345
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
//find reservation by her id and client cin
    public Reservation findByIdAndCin(int reservationId, String cin) {
    	try {
            statement = connection.createStatement();
            query = "select * from reservation " +
                    "where reservation_id = " + reservationId + "and cin_client="+ cin +";";
            resultSet = statement.executeQuery(query);

            if(resultSet.next()) {
                Time horaire = resultSet.getTime("horaire");
                int duree = resultSet.getInt("duree");

                //TODO Adapt to class Bureau
                Bureau bureau = BureauDAO.findById(resultSet.getInt("bureau_id"));

                String prenom = resultSet.getString("prenom_client");
                String nom = resultSet.getString("nom_client");
                String email = resultSet.getString("email_client");
                
                Client client = new Client(cin, nom, prenom, email);

                Reservation reservation =new Reservation(reservationId, horaire, bureau, client, duree);
                return reservation;
            }
            else return null;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    	
    //TODO Check if these methods need to be static

    public Reservation findById(int reservationId) {
        try {
            statement = connection.createStatement();
            query = "select * from reservation " +
                    "where reservation_id = " + reservationId + ";";
            resultSet = statement.executeQuery(query);

             if(resultSet.next()) {
                Time horaire = resultSet.getTime("horaire");
                int duree = resultSet.getInt("duree");

                //TODO Adapt to class Bureau
                Bureau bureau = BureauDAO.findById(resultSet.getInt("bureau_id"));

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

    public void createReservation(Reservation reservation) {
        try {
            statement = connection.createStatement();

            //int reservationId = reservation.getReservationId();
            Time horaire = reservation.getHoraire();
            int duree = reservation.getDuree();
            Client client = reservation.getClient();
            int bureauId = reservation.getBureau().getBureauId(); //TODO Adapt to class Bureau

            //TODO change cin to work as a String if changed in class Client
            query ="INSERT INTO reservation(cin_client, nom_client, prenom_client, email_client, horaire, duree, bureau_id) " +
                    "VALUES(" + client.getCin() + ", '" + client.getNom() + "', '" + client.getPrenom() +
                    "', '" + client.getEmail() + "', '" + horaire + "', " + duree + ", " + bureauId + ");";

            resultSet = statement.executeQuery(query);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //Updates "horaire" & "bureau_id"
    public void updateReservation(int reservationId, Time horaire, int bureauId) {
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

    //we can do better email/subject/body must be read from a file or something like that
    public void sendEmailVerification(Reservation reservation, JavaMailSender javamailsender) {
		SimpleMailMessage mail=new SimpleMailMessage();
		mail.setTo(reservation.getClient().getEmail());
		mail.setFrom("mohammedouttaleb245@gmail.com");
		mail.setSubject("Bank Reservation");
		mail.setText("Bonjour Mr "+reservation.getClient().getPrenom()+".\nNous vous confirmons que vous avez bien reserever votre place à l'agence... \nVoici votre clé de reservation : "+reservation.getReservationId()+".\nCordialement.");
		javamailsender.send(mail);
		 System.out.println("Mail sent ");
        return;
    }


}
