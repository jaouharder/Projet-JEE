package com.ReservationSystem.controllers;

import com.ReservationSystem.dao.BureauDAO;
import com.ReservationSystem.dao.ReservationDAO;
import com.ReservationSystem.model.Reservation;
import com.ReservationSystem.model.ReservationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin
public class ReservationController {

    @Autowired
    private ReservationDAO reservationDAO;
    @Autowired
    private BureauDAO bureau_service;
    
    @Autowired
    private JavaMailSender javamailsender;
	  
	
    @PostMapping("/addreservation")
    public int addReservation(@RequestBody ReservationInfo reservationInfo) {
    	 int reservationId= reservationDAO.createReservation(reservationInfo.getHoraire(), reservationInfo.getBureauId(), reservationInfo.getCin(), reservationInfo.getNom(), reservationInfo.getPrenom(), reservationInfo.getEmail());
    	 bureau_service.calculTaux(reservationInfo.getBureauId()); 
         return reservationId;
    }
    

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
    public void updateReservation(@RequestBody ReservationInfo reservation,
                                  @PathVariable(value = "id") int reservationId) {
    	bureau_service.calculTaux(reservationDAO.GetBuIdByReservationId(reservationId)); 
    	System.out.println("update controller");
        reservationDAO.updateReservation(reservationId, reservation.getHoraire(), reservation.getBureauId());
        
    	bureau_service.calculTaux(reservation.getBureauId());
    }

    @DeleteMapping("deletereservation/{id}")
    public boolean removeReservation(@PathVariable(value = "id") int reservationId) {
    	System.out.println("delete controller");
    	reservationDAO.deleteReservation(reservationId);
    	return true;
    }
    
    
    
    @PostMapping("/sendmail")
	public void SendMail(@RequestBody ReservationInfo reservation) {
    	reservationDAO.sendEmailVerification(reservation, javamailsender);		  
	}
    
    @PutMapping("setDuree/{id}")
    public void setDuree(@RequestBody int duree, @PathVariable(value = "id") int reservationId) {
        reservationDAO.setDuree(reservationId, duree);
    }

}