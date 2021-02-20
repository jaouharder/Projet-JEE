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

	public Timestamp getHoraire() {
		return horaire;
	}

	public void setHoraire(Timestamp horaire) {
		this.horaire = horaire;
	}

	public int getBureauId() {
		return bureauId;
	}

	public void setBureauId(int bureauId) {
		this.bureauId = bureauId;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
    
    
}