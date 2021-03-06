package com.ReservationSystem.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.ReservationSystem.model.Agence;
import com.ReservationSystem.model.Bureau;

class TestBureauDAO {
    
	//tester la methode findById() de la classe BureauDAO
	@Test
	void findByIdTest()  throws SQLException{
		BureauDAO bureaudao = new BureauDAO();
		
		Agence agence=null;
        Bureau bureau1 = new Bureau(1,"schèque",30,true);
        Bureau bureau2 = new Bureau(2,"scompte",50,true);
        Bureau bureau3 = new Bureau(3,"svairement",40,true);
        List<Bureau> bureaux = new ArrayList<Bureau>();
        bureaux.add(bureau1);
        bureaux.add(bureau2);
        bureaux.add(bureau3);
        agence = new Agence("CIH Kortoba","LOT Kortoba Meknes Maroc",(float)33.25,(float)-8.5,bureaux);
        agence.setId(1);
		
		Bureau bureauExpected = new Bureau(1,"schèque",30,agence,true);
		Bureau bureauReturned = bureaudao.findById(1);
		assertEquals(bureauExpected.getBureauId(),bureauReturned.getBureauId());
		assertEquals(bureauExpected.getService(),bureauReturned.getService());
		assertEquals(bureauExpected.getAgence().getId(),bureauReturned.getAgence().getId());
		
	}

}
