package auto_radnja.gume;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class AutoGumaTest {
	
	AutoGuma ag1;
	AutoGuma ag2;
	
	@BeforeEach
	void setUp() throws Exception {
		ag1=new AutoGuma();

		
		ag2=new AutoGuma();

	}

	@AfterEach
	void tearDown() throws Exception {
		ag1=null;
		ag2=null;
	}

	@Test
	void testAutoGuma() {
		AutoGuma ag=new AutoGuma();
		assertNotNull(ag);
		assertNull(ag.getMarkaModel());
		assertTrue(ag.getPrecnik()==-1);
		assertTrue(ag.getSirina()==-1);
		assertTrue(ag.getVisina()==-1);
		
	}

	@Test
	void testAutoGumaStringIntIntInt() {
		AutoGuma ag=new AutoGuma("Model 1", 21, 136, 50);
		assertNotNull(ag);
		assertEquals("Model 1", ag.getMarkaModel());
		assertEquals(21, ag.getPrecnik());
		assertEquals(136, ag.getSirina());
		assertEquals(50, ag.getVisina());
	}

	@Test
	void testSetMarkaModelNull() {
		Exception e=assertThrows(java.lang.NullPointerException.class, ()->ag1.setMarkaModel(null));
		assertEquals("Morate uneti marku i model", e.getMessage());
	}
	
	@Test
	void testSetMarkaModelStringLos() {
		Exception e=assertThrows(java.lang.IllegalArgumentException.class, ()->ag1.setMarkaModel("mo"));
		assertEquals("Marka i model moraju sadrzati bar 3 znaka", e.getMessage());
	}
	@Test
	void testSetMarkaModel() {
		ag1.setMarkaModel("Model 1 Marka 2");
		assertEquals("Model 1 Marka 2", ag1.getMarkaModel());
	}

	@Test
	void testSetPrecnikVanOpsega() {
		Exception e=assertThrows(java.lang.IllegalArgumentException.class, ()->ag1.setPrecnik(12));
		assertEquals("Precnik van opsega", e.getMessage());
	}
	
	@ParameterizedTest
	@CsvSource(
			{"13","14","15","18","21"})
	
	void testSetPrecnik(int precnik) {
		ag1.setPrecnik(precnik);
		assertEquals(precnik, ag1.getPrecnik());
	}

	@Test
	void testSetSirinaVanOpsega() {
		Exception e=assertThrows(java.lang.IllegalArgumentException.class, ()->ag1.setSirina(134));
		assertEquals("Sirina van opsega", e.getMessage());
	}
	
	@ParameterizedTest
	@CsvSource(
			{"135","145","155","265","355"})
	void testSetSirina(int sirina) {
		ag1.setSirina(sirina);
		assertEquals(sirina, ag1.getSirina());
	}

	@Test
	void testSetVisinaVanOpsega() {
		Exception e=assertThrows(java.lang.IllegalArgumentException.class, ()->ag1.setVisina(24));
		assertEquals("Visina van opsega", e.getMessage());
	}

	@ParameterizedTest
	@CsvSource(
			{"25","35","65","85","95"})
	void testSetVisina(int visina) {
		ag1.setVisina(visina);
		assertEquals(visina, ag1.getVisina());
	}
	
	@Test
	void testToString() {
		ag1.setMarkaModel("Model 1");
		ag1.setPrecnik(15);
		ag1.setSirina(136);
		ag1.setVisina(95);
		
		String s=ag1.toString();
		assertTrue(s.contains(ag1.getMarkaModel()));
		assertTrue(s.contains(ag1.getPrecnik()+""));
		assertTrue(s.contains(ag1.getSirina()+""));
		assertTrue(s.contains(ag1.getVisina()+""));
	}

	@Test
	void testEqualsObject() {
		AutoGuma ag=ag1;
		assertTrue(ag.equals(ag1));
	}
	
	@Test
	void testEqualsObjectNull() {
		AutoGuma ag=null;
		assertFalse(ag1.equals(ag));
	}
	
	@Test
	void testEqualsObjectDrugaKlasa() {
		String s=new String();
		assertFalse(ag1.equals(s));
	}
	
	@ParameterizedTest
	@CsvSource(
	{"Marka 1, Marka 1, 14, 14, 135, 135, 25, 25, true",
	 "Marka 1, Marka 2, 14, 14, 135, 135, 25, 25, false",
	 "Marka 1, Marka 1, 14, 16, 135, 135, 25, 25, false",
	 "Marka 1, Marka 1, 14, 14, 135, 136, 25, 25, false",
	 "Marka 1, Marka 1, 14, 14, 135, 135, 25, 85, false"})
	void testEqualsObjectSveOk(String model1,String model2,int precnik1,int precnik2,int sirina1,int sirina2,int visina1,int visina2,Boolean vrednost) {
		ag1.setMarkaModel(model1);
		ag1.setPrecnik(precnik1);
		ag1.setSirina(sirina1);
		ag1.setVisina(visina1);
		
		ag2.setMarkaModel(model2);
		ag2.setPrecnik(precnik2);
		ag2.setSirina(sirina2);
		ag2.setVisina(visina2);
		
		assertEquals(vrednost, ag1.equals(ag2));
		
	}
	
	

}
