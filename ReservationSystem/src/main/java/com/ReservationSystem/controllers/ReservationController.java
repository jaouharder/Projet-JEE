package com.ReservationSystem.controllers;

import com.ReservationSystem.dao.ReservationDAO;
import com.ReservationSystem.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ReservationController {

    @Autowired
    private ReservationDAO reservationDAO;


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

    @GetMapping("/reservations")
    public List<Reservation> getAllReservations() {
        return reservationDAO.findAll();
    }

    @GetMapping("/reservation/{id}")
    public Reservation getReservationById(@PathVariable(value = "id") int reservationId) {
        return reservationDAO.findById(reservationId);
    }

    @PatchMapping("/updatereservation/{id}")
    public void updateReservation(@RequestBody Reservation reservation,
                                  @PathVariable(value = "id") int reservationId) {
        reservationDAO.updateReservation(reservationId, reservation.getHoraire(), reservation.getBureau().getBureauId());
    }

    @DeleteMapping("/deletereservation/{id}")
    public void removeReservation(@PathVariable(value = "id") int reservationId) {
        reservationDAO.deleteReservation(reservationId);
    }
}
