package com.ReservationSystem.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

public class ReservationInfo {
	private int reservationId;
	private Timestamp horaire;
	private int bureauId;
	private String cin;
	private String nom;
	private String prenom;
	private String email;

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

	public int getReservationId() {
		return reservationId;
	}

	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}
}