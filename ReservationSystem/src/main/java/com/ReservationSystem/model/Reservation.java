package com.ReservationSystem.model;

import javax.validation.constraints.NotNull;


import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Duration;
import java.time.Instant;
import java.sql.Timestamp;

public class Reservation {

    private int reservationId;
    private @NotNull Timestamp horaire;
    @NotNull
    private Bureau bureau;
    @NotNull
    private Client client;
    private int duree;

    public Reservation(@JsonProperty("horaire") @NotNull Timestamp horaire,@JsonProperty("bureau") @NotNull Bureau bureau,@JsonProperty("client") @NotNull Client client) {
        //this.reservationId = reservationId;
        this.horaire = horaire;
        this.bureau = bureau;
        this.client = client;
        this.duree = 0;
    }
    //this constructor contains field "duree"
    public Reservation(int reservationId, @NotNull Timestamp horaire, @NotNull Bureau bureau, @NotNull Client client, int duree) {
        this.reservationId = reservationId;
        this.horaire = horaire;
        this.bureau = bureau;
        this.client = client;
        this.duree = duree;
    }
    //this constructor works out "duree" from two instants instantDebut & instantFin
    public Reservation(int reservationId, @NotNull Timestamp horaire, @NotNull Bureau bureau, @NotNull Client client, Instant instantDebut, Instant instantFin) {
        this.reservationId = reservationId;
        this.horaire = horaire;
        this.bureau = bureau;
        this.client = client;
        this.duree = (int) Duration.between(instantDebut, instantFin).getSeconds();
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public @NotNull Timestamp getHoraire() {
        return horaire;
    }

    public void setHoraire(Timestamp horaire) {
        this.horaire = horaire;
    }

    public Bureau getBureau() {
        return bureau;
    }

    public void setBureau(Bureau bureau) {
        this.bureau = bureau;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public int getDuree() { return duree; }

    public void setDuree(int duree) { this.duree = duree; }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId=" + reservationId +
                ", horaire=" + horaire +
                ", bureau=" + bureau +
                ", client=" + client +
                ", duree=" + duree +
                '}';
    }
}
