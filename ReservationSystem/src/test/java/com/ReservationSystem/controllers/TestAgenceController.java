package com.ReservationSystem.controllers;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import com.ReservationSystem.model.Agence;
import com.ReservationSystem.model.Bureau;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TestAgenceController {

	@Autowired
    private TestRestTemplate testRestTemplate;
	
	//tester GetAgencybyID() de la classe Agencecontroller
	@Test
	void GetAgencybyIDTest() throws Exception{
		Agence agenceExpected=null;
        Bureau bureau1 = new Bureau(1,"schèque",30,true);
        Bureau bureau2 = new Bureau(2,"scompte",50,true);
        Bureau bureau3 = new Bureau(3,"svairement",40,true);
        List<Bureau> bureaux = new ArrayList<Bureau>();
        bureaux.add(bureau1);
        bureaux.add(bureau2);
        bureaux.add(bureau3);
        agenceExpected = new Agence("CIH Kortoba","LOT Kortoba Meknes Maroc",(float)33.25,(float)-8.5,bureaux);
        agenceExpected.setId(1);
		
        ResponseEntity<Agence> agenceReturned = testRestTemplate.getForEntity( "/agency/1", Agence.class);
        assertEquals(agenceExpected.getNom(), agenceReturned.getBody().getNom());
        assertEquals(agenceExpected.getLatitude(), agenceReturned.getBody().getLatitude());
        assertEquals(agenceExpected.getLongitude(), agenceReturned.getBody().getLongitude());
		
	}
	
	//tester addgencybyID() de la classe Agencecontroller
	@Test
	void addAgencyTest() {
		Agence agenceToCreate;
		Bureau bureau1 = new Bureau(4,"schèque",56,true);
        Bureau bureau2 = new Bureau(5,"scompte",70,true);
        Bureau bureau3 = new Bureau(6,"svairement",20,true);
        List<Bureau> bureaux = new ArrayList<Bureau>();
        bureaux.add(bureau1);
        bureaux.add(bureau2);
        bureaux.add(bureau3);
        agenceToCreate = new Agence("CIH AGDAL","AGDAL RABAT Maroc",(float)33.24,(float)-8.6,bureaux);
        
        HttpEntity<Agence> request = new HttpEntity<>(agenceToCreate);
        ResponseEntity<Boolean> response = testRestTemplate.postForEntity( "/addagency",request,Boolean.class);
        assertEquals(true, response.getBody());
        
	}
	

}
