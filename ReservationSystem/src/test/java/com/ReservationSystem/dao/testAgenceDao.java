package com.ReservationSystem.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.ReservationSystem.model.Agence;
import com.ReservationSystem.model.Bureau;

class testAgenceDao {
	
	//tester la methode findById() de la classe AgenceDAO
	@Test
	void findByIdTest() {
		AgenceDAO agencedao = new AgenceDAO();
		Agence agenceExpected=null, agenceReturned=agencedao.findById(1);
        Bureau bureau1 = new Bureau(1,"schèque",30,true);
        Bureau bureau2 = new Bureau(2,"scompte",50,true);
        Bureau bureau3 = new Bureau(3,"svairement",40,true);
        List<Bureau> bureaux = new ArrayList<Bureau>();
        bureaux.add(bureau1);
        bureaux.add(bureau2);
        bureaux.add(bureau3);
        agenceExpected = new Agence("CIH Kortoba","LOT Kortoba Meknes Maroc",(float)33.25,(float)-8.5,bureaux);
        agenceExpected.setId(1);
        assertEquals(agenceExpected.getId(), agenceReturned.getId());
		assertEquals(agenceExpected.getLatitude(), agenceReturned.getLatitude());
		assertEquals(agenceExpected.getLongitude(), agenceReturned.getLongitude());
		
	}

}
