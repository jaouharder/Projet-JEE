package com.ReservationSystem.model;

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
	 @Max(100)
	 @Min(0)
	 private int  disponibilite;
	 //we have to add List<Bureau> attribute to this class
	 
	
	 public Agence(@NotNull @Size(min = 1, max = 6) String nom, @NotNull @Size(min = 1, max = 6) String localisation,
				@Max(100) @Min(0) int disponibilite) {
		    setNom(nom);
			setLocalisation(localisation);
			setDisponibilite(disponibilite);
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
	public int getDisponibilite() {
		return disponibilite;
	}
	public void setDisponibilite(int disponibilite) {  
			  this.disponibilite = disponibilite;
	}

	@Override
	public String toString() {
		return "Agence [id=" + id + ", nom=" + nom + ", localisation=" + localisation + ", disponibilite="
				+ disponibilite + "]";
	}
	 
	
	 
	 
	 
}
