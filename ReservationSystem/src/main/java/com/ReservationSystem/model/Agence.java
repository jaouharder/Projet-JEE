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
	//we have to add attribute to this class
	 private  List<Bureau> bureauList;
	 
	 
	
	 public Agence(@NotNull @Size(min = 1, max = 6) String nom, @NotNull @Size(min = 1, max = 6) String localisation) {
		    setNom(nom);
			setLocalisation(localisation);
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






	@Override
	public String toString() {
		return "Agence [id=" + id + ", nom=" + nom + ", localisation=" + localisation + "]";
	}
	
	

 
	 
	
	 
	 
	 
}
