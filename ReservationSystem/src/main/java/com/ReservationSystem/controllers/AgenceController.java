package com.ReservationSystem.controllers;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ReservationSystem.dao.AgenceDAO;
import com.ReservationSystem.model.Agence;


@RestController
public class AgenceController {
      
	  @Autowired
	  private AgenceDAO agence_service;
	  
	  
	  
	  /*validation dont work i dont know why !!!!!!!!!!!!*/
	  @PostMapping("/addagency")
	  public boolean addAgency(@RequestBody @Valid Agence agence,Errors err ) {
		  if(err.hasErrors()) {
			  //System.out.println(agence);
			  return false;
		  }
		   agence_service.createAgency(agence);
		  return true;
	  }
	  
	  @GetMapping("/agencies")
	  public List<Agence> GetAllAgencies(){
		  return agence_service.findAll();
	  }
	
	
	  @GetMapping("/agency/id")
      public Agence GetAgencybyID(@PathVariable(name = "id") int agence_id) {
	       
		  return  agence_service.findById(agence_id);   	
	}
	
}
