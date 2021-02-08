/*****
By DERROUICH JAOUHAR 
ENSIAS-GL1
*****/

package com.ReservationSystem.model;

import javax.validation.constraints.NotNull;

public class Bureau {

	private int bureauId;
	@NotNull
	private String service;
	@NotNull
	private int bureau_disp;
	@NotNull
	private Agence agence;
	
	public Bureau() {
		super();
	}

	

	public Bureau(int bureauId) {
		super();
		this.bureauId = bureauId;
	}

	public Bureau(int bureauId, @NotNull String service, @NotNull int bureau_disp, @NotNull Agence agence) {
		super();
		this.bureauId = bureauId;
		this.service = service;
		this.bureau_disp = bureau_disp;
		this.agence = agence;
	}
	public int getBureauId() {
		return bureauId;
	}
	public void setBureauId(int bureauId) {
		this.bureauId = bureauId;
	}
	public String getService() {
		return service;
	}
	public Bureau(@NotNull String service, @NotNull Agence agence) {
		super();
		this.service = service;
		this.agence = agence;
	}



	public void setService(String service) {
		this.service = service;
	}
	public int getBureau_disp() {
		return bureau_disp;
	}
	public void setBureau_disp(int bureau_disp) {
		this.bureau_disp = bureau_disp;
	}
	public Agence getAgence() {
		return agence;
	}
	public void setAgence(Agence agence) {
		this.agence = agence;
	}
	@Override
	public String toString() {
		return "Bureau [bureauId=" + bureauId + ", service=" + service + ", bureau_disp=" + bureau_disp + ", agence="
				+ agence + "]";
	}

}
