package com.ReservationSystem.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

public class ReservationInfo {
    public Timestamp horaire;
    public int bureauId;
    public String cin;
    public String nom;
    public String prenom;
    public String email;

    public ReservationInfo(@JsonProperty("horaire")Timestamp horaire, @JsonProperty("bureauId")int bureauId, @JsonProperty("cin")String cin, @JsonProperty("nom")String nom, @JsonProperty("prenom")String prenom, @JsonProperty("email")String email) {
        this.horaire = horaire;
        this.bureauId = bureauId;
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }
}