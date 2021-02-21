package com.ReservationSystem.controllers;

import com.ReservationSystem.dao.ReservationDAO;
import com.ReservationSystem.model.Client;
import com.ReservationSystem.model.Reservation;
import com.ReservationSystem.model.ReservationInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.List;

@RestController
@CrossOrigin
public class ReservationController {

    @Autowired
    private ReservationDAO reservationDAO;
    
    @Autowired
    private JavaMailSender javamailsender;
	  
	  
    /*
    @PostMapping("/testpost")
    public void testPostReservation(@RequestBody Client client) {
        System.out.println(client);
    }
    */

    @PostMapping("/addreservation")
    public int addReservation(@RequestBody ReservationInfo reservationInfo) {

        return reservationDAO.createReservation(reservationInfo.horaire, reservationInfo.bureauId, reservationInfo.cin, reservationInfo.nom, reservationInfo.prenom, reservationInfo.email);
    }
    /*
    @PostMapping("/addreservation")
    public boolean addReservation(@RequestBody Reservation reservation, Errors errors) {
        if (errors.hasErrors()) {
            return false;
        }
        else {
            reservationDAO.createReservation(reservation);
            return true;
        }
    }*/

    @GetMapping("/reservations")
    public List<Reservation> getAllReservations() {
    	System.out.println("reserva");
        return reservationDAO.findAll();
    }

    @GetMapping("/reservationbyagence/{id}")
    public List<Reservation> getReservationByAgenceId(@PathVariable(value = "id") int reservationId) {
        return reservationDAO.findByAgenceId(reservationId);
    }

    @GetMapping("/reservation/bureau/{id}")
    public List<Reservation> getReservationByBureauId(@PathVariable(value = "id") int bureauId) {
        return reservationDAO.findByBureauId(bureauId);
    }

    @GetMapping("/reservation/{id}")
    public Reservation getReservationById(@PathVariable(value = "id") int reservationId) {
        return reservationDAO.findById(reservationId);
    }

    @PutMapping("updatereservation/{id}")
    public void updateReservation(@RequestBody Reservation reservation,
                                  @PathVariable(value = "id") int reservationId) {
    	System.out.println("update controller");
        reservationDAO.updateReservation(reservationId, reservation.getHoraire(), reservation.getBureau().getBureauId());
    }

    @DeleteMapping("deletereservation/{id}")
    public boolean removeReservation(@PathVariable(value = "id") int reservationId) {
    	System.out.println("delete controller");
    	//reservationDAO.deleteReservation(reservationId);
    	return true;
    }
    
    
    
    @PostMapping("/sendmail")
	public void SendMail(@RequestBody ReservationInfo reservation) {
    	reservationDAO.sendEmailVerification(reservation, javamailsender);		  
	}

}
