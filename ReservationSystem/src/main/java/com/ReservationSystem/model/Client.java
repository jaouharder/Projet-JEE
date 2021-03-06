package com.ReservationSystem.model;


public class Client {

	//variables
		private String cin;
		private String nom;
		private String prenom;
		private String localisation;
		private String email;

	//default constructor
		public Client() {
			
		}

	//constructor with all variables
		public Client(String cin, String nom, String prenom, String localisation, String email) {

			super();
			this.cin = cin;
			this.nom = nom;
			this.prenom = prenom;
			this.localisation = localisation;
			this.email = email;
		}
	//constructor without localisation
			public Client(String cin, String nom, String prenom, String email) {
				super();
				this.cin = cin;
				this.nom = nom;
				this.prenom = prenom;
				this.email = email;
			}
	//getters & setters			
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
			public String getLocalisation() {
				return localisation;
			}
			public void setLocalisation(String localisation) {
				this.localisation = localisation;
			}
			public String getEmail() {
				return email;
			}
			public void setEmail(String email) {
				this.email = email;
			}

}
