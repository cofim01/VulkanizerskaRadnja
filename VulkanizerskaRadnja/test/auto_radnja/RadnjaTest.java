package auto_radnja;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import auto_radnja.gume.AutoGuma;

public abstract class RadnjaTest {
	
	public abstract Radnja getInstance();
	
	Radnja r;
	AutoGuma ag1;
	AutoGuma ag2;
	
	@BeforeEach
	void setUp() throws Exception {
		r=getInstance();
		
		ag1=new AutoGuma();
		ag1.setMarkaModel("Model 1");
		ag1.setPrecnik(16);
		ag1.setSirina(136);
		ag1.setVisina(45);
		
		ag2=new AutoGuma();
		ag2.setMarkaModel("Model 2");
		ag2.setPrecnik(18);
		ag2.setSirina(145);
		ag2.setVisina(85);
		
	}

	@AfterEach
	void tearDown() throws Exception {
		r=null;
		ag1=null;
		ag2=null;
	}

	@Test
	void testDodajGumuNull() {
		Exception e=assertThrows(java.lang.NullPointerException.class, ()->r.dodajGumu(null));
		assertEquals("Guma ne sme biti null", e.getMessage());
	}
	
	@Test
	void testDodajGumuPostoji() {
		r.dodajGumu(ag1);
		Exception e=assertThrows(java.lang.RuntimeException.class, ()->r.dodajGumu(ag1));
		assertEquals("Guma vec postoji", e.getMessage());
	}
	
	@Test
	void testDodajGumuVisePrimeraka() {
		r.dodajGumu(ag1);
		r.dodajGumu(ag2);
		List<AutoGuma> sve=r.vratiSveGume();
		assertEquals(2,sve.size() );
		assertEquals(ag2, sve.get(0));
		assertEquals(ag1, sve.get(1));
	}
	
	@Test
	void testDodajGumu() {
		r.dodajGumu(ag1);
		List<AutoGuma> sve=r.vratiSveGume();
		assertEquals(1,sve.size() );
		assertTrue(sve.contains(ag1));
	}
	
	
	@Test
	void testPronadjiGumuNull() {
		assertEquals(null, r.pronadjiGumu(null));
	}
	
	@Test
	void testPronadjiGumuNemaRezultata() {
		r.dodajGumu(ag1);
		r.dodajGumu(ag2);
		
		List<AutoGuma> rezultat=r.pronadjiGumu("Model 3");
		assertEquals(0, rezultat.size());
		
	}
	
	@Test
	void testPronadjiGumu() {
		r.dodajGumu(ag1);
		r.dodajGumu(ag2);
		AutoGuma g3=new AutoGuma();
		g3.setMarkaModel("Model 1");
		r.dodajGumu(g3);
		List<AutoGuma> rezultat=r.pronadjiGumu("Model 1");
		assertEquals(2, rezultat.size());
		assertTrue(rezultat.contains(ag1));
		assertTrue(rezultat.contains(g3));
		
	}
	

	

}
