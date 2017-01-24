package de.hrw.wi.service;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;



import org.junit.Before;
import org.junit.Test;

import de.hrw.wi.business.Order;
import de.hrw.wi.persistence.BestellungReadInterface;
import de.hrw.wi.persistence.BestellungWriteInterface;
import de.hrw.wi.persistence.BestellungRealDatabase;

public class BestellungenManagementIntegrationTest {

	
	private BestellungenManagementInterface bmService;
	
	
	@Before
	public void setUp() {
		//Bestellung Real Database
		BestellungReadInterface dbRead = new BestellungRealDatabase();
		BestellungWriteInterface dbWrite = new BestellungRealDatabase();
		bmService = new BestellungenManagement(dbRead, dbWrite);
	}
	
	@Test
	public void testGetAllOrders() throws SQLException {
		bmService.addBestellung(1, 250, "neu", "12.12.2016", 0, 12, 10);
		bmService.addBestellung(2, 1000, "neu", "30.12.2016", 6, 4, 2);
		
		assertEquals(2, bmService.getAllOrders().size());
		
		bmService.deleteBestellung(1, "kein grund angegeben");
		bmService.deleteBestellung(2, "kein grund angegeben");
		
	}
	   
	
	@Test
	public void testgetAllKundenbestellungen() throws SQLException {
		
		bmService.addBestellung(3, 151, "neu", "05.11.2015", 75, 55, 6);
		assertEquals(1, bmService.getAllKundenbestellungen(75).size());
		
		bmService.deleteBestellung(3, "kein grund angegeben");
	}
	
	
	@Test
	public void testGetGesamtbetrag() throws SQLException {
		
		bmService.addBestellung(55, 545, "neu", "05.01.2017", 99, 254545, 22);
		bmService.addBestellung(85, 5545, "neu", "05.04.2014", 88, 121, 22);
		
		
		assertEquals(5545, bmService.getGesamtbetrag(85));
		assertEquals(545, bmService.getGesamtbetrag(55));
		
		bmService.deleteBestellung(55, "kein grund angegeben");
		bmService.deleteBestellung(85, "kein grund angegeben");
		
	}
	
	@Test
	public void testGetBestellDaten() throws SQLException {
		
		bmService.addBestellung(8, 2, "neu", "31.04.2016", 887, 5487, 2);
		bmService.addBestellung(87, 5, "neu", "05.12.2014", 95, 84, 3);
		bmService.addBestellung(155, 28, "neu", "31.05.2016", 448, 77, 4);
	
		
		
		assertEquals(1, bmService.getGesamtBestelldaten(55).size());
		assertEquals(1, bmService.getGesamtBestelldaten(87).size());
		assertEquals(1, bmService.getGesamtBestelldaten(8).size());
		
	
		
		
	
		
		
		bmService.deleteBestellung(155, "Mangelware");
		bmService.deleteBestellung(87, "Produkt nicht wie erwartet");
		bmService.deleteBestellung(8, "Zu klein ausgefallen");
		
		String grund = "";

		for (Order s : bmService.getGesamtBestelldaten(155)) {
			grund = s.getStatus();
		}
		
		System.out.println("Der Rückgabegrund ist : " + grund);
	
	
	}
	
	@Test
	
	public void testGetBestelldatum() throws SQLException {
		
		bmService.addBestellung(6, 23, "alt", "12.11.2015", 5, 9, 1);
		bmService.addBestellung(21, 78, "alt", "13.12.2015", 545545, 1, 9);

		
		assertEquals("12.11.2015", bmService.getBestelldatum(6));
		assertEquals("13.12.2015", bmService.getBestelldatum(21));
		
		bmService.deleteBestellung(6, "kein grund angegeben");
		bmService.deleteBestellung(21, "kein grund angegeben");
	}
	
	@Test
	public void  testGetBestelldatenADay() throws SQLException {
	
		bmService.addBestellung(77, 32, "alt", "12.12.2012", 44, 21, 55);
	
		
		assertEquals(1, bmService.getAllBestellungenADay("12.12.2012").size());
		
		bmService.deleteBestellung(77, "kein grund angegeben");
	}
	
	@Test
	public void testGetAllKundenADay() throws SQLException {
		bmService.addBestellung(655, 2554, "neu", "02.05.2016", 22, 65, 4);
		bmService.addBestellung(998, 31, "neu", "02.05.2016", 987, 658, 33);
		
		
		assertEquals(2, bmService.getAllKundenADay("02.05.2016").size());
		
		
		bmService.deleteBestellung(655, "kein grund angegeben");
		bmService.deleteBestellung(998, "kein grund angegeben");
		
	}
	
}
