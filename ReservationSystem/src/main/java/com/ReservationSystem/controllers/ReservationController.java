package com.ReservationSystem.controllers;

import com.ReservationSystem.dao.ReservationDAO;
import com.ReservationSystem.model.Bureau;
import com.ReservationSystem.model.Client;
import com.ReservationSystem.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.List;

@RestController
@CrossOrigin
public class ReservationController {

    @Autowired
    private ReservationDAO reservationDAO;
    
    @Autowired
	  JavaMailSender javamailsender;
	  
	  
	  


    @PostMapping("/addreservation")
    public boolean addReservation(@RequestBody Reservation reservation, Errors errors) {
        if (errors.hasErrors()) {
            return false;
        }
        else {
            reservationDAO.createReservation(reservation);
            return true;
        }
    }

    @GetMapping("reservations")
    public List<Reservation> getAllReservations() {
    	System.out.println("reserva");
        return reservationDAO.findAll();
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
	public void SendMail(@RequestBody Reservation reservation) {
    	reservationDAO.sendEmailVerification(reservation, javamailsender);		  
	}
}
