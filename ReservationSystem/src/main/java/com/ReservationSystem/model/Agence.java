package com.ReservationSystem.model;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class Agence {

	 private int id;
	 @NotNull
	 @Size(min = 1,max = 6)
	 private String nom;
	 @NotNull
	 @Size(min = 1,max = 6)
	 private String localisation;
	 
	 private float latitude;
	 
	 private float longitude;
	 private  List<Bureau> bureauList;
	 
	 
	 
	

	public Agence(@NotNull @Size(min = 1, max = 6) String nom,
			@NotNull @Size(min = 1, max = 6) String localisation, float latitude, float longitude,
			List<Bureau> bureauList) {
		this.nom = nom;
		this.localisation = localisation;
		this.latitude = latitude;
		this.longitude = longitude;
		this.bureauList = bureauList;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getLocalisation() {
		return localisation;
	}
	public void setLocalisation(String localisation) {		
		this.localisation = localisation;
	}

	public float getLatitude() {
		return latitude;
	}






	public void setLatitude(float d) {
		this.latitude = d;
	}






	public float getLongitude() {
		return longitude;
	}






	public void setLongitude(float d) {
		this.longitude = d;
	}






	public List<Bureau> getBureauList() {
		return bureauList;
	}






	public void setBureauList(List<Bureau> bureauList) {
		this.bureauList = bureauList;
	}






	@Override
	public String toString() {
		return "Agence [id=" + id + ", nom=" + nom + ", localisation=" + localisation + "]";
	}
	
	

 
	 
	
	 
	 
	 
}
