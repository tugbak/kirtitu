package de.hrw.wi.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;

import de.hrw.wi.persistence.BestellungReadInterface;
import de.hrw.wi.persistence.BestellungWriteInterface;

public class BestellungenManagementTest {

	private BestellungenManagementInterface bmService;

	@Before
	public void setup() {

		// MockObjekt erstellen

		BestellungReadInterface bmRead = Mockito.mock(BestellungReadInterface.class);
		BestellungWriteInterface bmWrite = Mockito.mock(BestellungWriteInterface.class);

		when(bmRead.getBetrag(0)).thenReturn(5556);
		when(bmRead.getStatus(0)).thenReturn("aktiv");
		when(bmRead.getBestelldatum(0)).thenReturn("12.01.2015");
		when(bmRead.getCustomerId(0)).thenReturn(1);
		when(bmRead.getProductId(0)).thenReturn(25);
		when(bmRead.getCustomerId(1)).thenReturn(5);
		when(bmRead.getCustomerId(2)).thenReturn(3);
		when(bmRead.getAnzahl(8)).thenReturn(90);

		// alle Ausgaben
		HashSet<Integer> bst = new HashSet<Integer>();
		bst.add(2);
		bst.add(2);
		bst.add(7);
		bst.add(82);
		bst.add(1254);
		when(bmRead.getAllProdukteADay("01.05.2013")).thenReturn(bst);

		HashSet<Integer> bst1 = new HashSet<Integer>();
		bst1.add(8);
		bst1.add(745);
		bst1.add(879522);
		when(bmRead.getAllProdukteADay("01.06.2013")).thenReturn(bst1);

		HashSet<Integer> bst2 = new HashSet<Integer>();
		bst2.add(9451);
		bst2.add(210512);
		bst2.add(7716325);
		when(bmRead.getAllProdukteADay("01.07.2013")).thenReturn(bst2);

		HashSet<Integer> produkta = new HashSet<Integer>();
		produkta.add(544);

		when(bmRead.getAnzahlProdukt("01.05.2013", 2)).thenReturn(produkta);
		HashSet<Integer> produktb = new HashSet<Integer>();
		produktb.add(729);

		when(bmRead.getAnzahlProdukt("01.05.2013", 7)).thenReturn(produktb);
		HashSet<Integer> produktc = new HashSet<Integer>();
		produktc.add(913);

		when(bmRead.getAnzahlProdukt("01.05.2013", 82)).thenReturn(produktc);

		HashSet<Integer> produktd = new HashSet<Integer>();
		produktd.add(134);

		when(bmRead.getAnzahlProdukt("01.05.2013", 1254)).thenReturn(produktd);

		HashSet<Integer> produkte = new HashSet<Integer>();
		produkte.add(212);

		when(bmRead.getAnzahlProdukt("01.06.2013", 8)).thenReturn(produkte);
		HashSet<Integer> produktf = new HashSet<Integer>();
		produktf.add(4545);

		when(bmRead.getAnzahlProdukt("01.06.2013", 745)).thenReturn(produktf);
		HashSet<Integer> produktg = new HashSet<Integer>();
		produktg.add(20);

		when(bmRead.getAnzahlProdukt("01.06.2013", 879522)).thenReturn(produktg);

		HashSet<Integer> produkth = new HashSet<Integer>();
		produkth.add(7265);
		when(bmRead.getAnzahlProdukt("01.07.2013", 9451)).thenReturn(produkth);

		HashSet<Integer> produkti = new HashSet<Integer>();
		produkti.add(7265);
		when(bmRead.getAnzahlProdukt("01.07.2013", 210512)).thenReturn(produkti);

		HashSet<Integer> produktj = new HashSet<Integer>();
		produktj.add(7265);
		when(bmRead.getAnzahlProdukt("01.07.2013", 7716325)).thenReturn(produktj);

		when(bmRead.getAnzahl(8)).thenReturn(15);
		when(bmRead.getAnzahl(9)).thenReturn(22);
		when(bmRead.getAnzahl(10)).thenReturn(4);

		HashSet<Integer> kundenID = new HashSet<Integer>();
		kundenID.add(54);
		kundenID.add(17);
		when(bmRead.getAllKundenADay("01.05.2013")).thenReturn(kundenID);

		HashSet<Integer> bestellnummern = new HashSet<Integer>();
		bestellnummern.add(0);

		when(bmRead.getAllBestellnummer()).thenReturn(bestellnummern);

		HashSet<Integer> kundenbestellnummern = new HashSet<Integer>();
		kundenbestellnummern.add(0);

		when(bmRead.getBestellnummer(1)).thenReturn(kundenbestellnummern);

		// Bestellungen, die an einem Tag durchgeführt wurden
		HashSet<Integer> tagesbestellnummern = new HashSet<Integer>();
		tagesbestellnummern.add(0);
		tagesbestellnummern.add(10);
		when(bmRead.getBestellnummerForADay("12.01.2015")).thenReturn(tagesbestellnummern);

		HashSet<Integer> tagesbestellnummerna = new HashSet<Integer>();
		tagesbestellnummerna.add(1);
		tagesbestellnummerna.add(2);
		tagesbestellnummerna.add(5);
		when(bmRead.getBestellnummerForADay("12.01.2015")).thenReturn(tagesbestellnummerna);

		// Anzahl der Kunden, die an einem Tag etwas bestellt haben
		HashSet<Integer> kunden = new HashSet<Integer>();
		kunden.add(0);
		kunden.add(46);
		kunden.add(23);
		when(bmRead.getAllKundenADay("12.01.2015")).thenReturn(kunden);

		HashSet<Integer> kunde = new HashSet<Integer>();
		kunde.add(1);
		kunde.add(6);
		when(bmRead.getAllKundenADay("02.03.2016")).thenReturn(kunde);

		// Für die Anzahl der Produkte
		HashSet<Integer> produkt1 = new HashSet<Integer>();
		produkt1.add(1);
		produkt1.add(2);

		when(bmRead.getAnzahl(1)).thenReturn(5);
		when(bmRead.getAnzahl(2)).thenReturn(4);
		when(bmRead.getAnzahlProdukt("12.01.2015", 1)).thenReturn(produkt1);

		bmService = new BestellungenManagement(bmRead, bmWrite);

		// Bestellhistorie
		when(bmService.getBestelldatum(52)).thenReturn("01.05.2013");
		when(bmService.getBestelldatum(30)).thenReturn("01.06.2013");
		when(bmService.getBestelldatum(22)).thenReturn("01.07.2013");
		// when(bmService.getAnzahlProdukt("01.05.2013", 2)).thenReturn(bst);

	}

	@Test
	public void testGetAllOrders() {
		assertEquals(1, bmService.getAllOrders().size());
	}

	@Test
	public void testgetAllKundenbestellungen() {
		assertEquals(1, bmService.getAllKundenbestellungen(1).size());
	}

	@Test
	public void testGetGesamtbetrag() {
		assertEquals(5556.0, bmService.getGesamtbetrag(0), 0.1);
	}

	@Test
	public void testGetBestellDaten() {
		assertEquals(1, bmService.getGesamtBestelldaten(0).size());
	}

	@Test
	public void testGetBestelldatum() {
		assertEquals("12.01.2015", bmService.getBestelldatum(0));
	}

	@Test
	public void testGetBestelldatenADay() {
		System.out.println("Bestellnummern für den 12.01.2015 : ");
		System.out.println(bmService.getAllBestellungenADay("12.01.2015"));
		System.out.println("_________________________________________________________________");
		assertEquals(3, bmService.getAllBestellungenADay("12.01.2015").size());

	}

	@Test
	public void testGetAllKundenADay() {
		System.out.println("Kundennummern, die am 12.01.2015 Bestellung durchgeführt haben: ");
		System.out.println(bmService.getAllKundenADay("12.01.2015"));
		System.out.println("_________________________________________________________________");
		assertEquals(3, bmService.getAllKundenADay("12.01.2015").size());

	}

	@Test
	public void testGetAnzahl() {
		assertEquals(2, bmService.getAnzahlProdukt("12.01.2015", 1).size());
		List<Integer> productSet = new ArrayList<Integer>();
		productSet.addAll(bmService.getAnzahlProdukt("12.01.2015", 1));

		Collections.sort(productSet);
		System.out.println("_________________________________________________________________");
		System.out.println("Produkte werden nach Anzahl sortiert: " + productSet);
		System.out.println("_________________________________________________________________");
	}

	@Test
	public void testBestellungADay() {

		List<Integer> kundenSet = new ArrayList<Integer>();
		kundenSet.addAll(bmService.getAllKundenADay("01.05.2013"));

		List<Integer> produktSet = new ArrayList<Integer>();
		produktSet.addAll(bmService.getAllProdukteADay("01.05.2013"));

		List<Integer> anzahlSet = new ArrayList<Integer>();
		anzahlSet.addAll(bmService.getAnzahlProdukt("01.05.2013", 2));
		anzahlSet.addAll(bmService.getAnzahlProdukt("01.05.2013", 7));
		anzahlSet.addAll(bmService.getAnzahlProdukt("01.05.2013", 82));
		anzahlSet.addAll(bmService.getAnzahlProdukt("01.05.2013", 1254));

		System.out.println("ID der Kunden, die an diesem Tag eine Bestellung durchgeführt haben: ");
		System.out.println(kundenSet);
		System.out.println("ID der Produkte, die an diesem Tag bestellt wurden: ");
		System.out.println(produktSet);
		System.out.println("Anzahl der bestellten Produkte: ");
		System.out.println(anzahlSet);
		System.out.println("_________________________________________________________________");

		assertEquals(4, bmService.getAllProdukteADay("01.05.2013").size());

	}

	@Test
	public void testBestellhistorie() {

		List<String> bestellhistorie = new ArrayList<String>();
		bestellhistorie.add(bmService.getBestelldatum(52));
		bestellhistorie.add(bmService.getBestelldatum(30));
		bestellhistorie.add(bmService.getBestelldatum(22));
		Collections.sort(bestellhistorie);
		System.out.println("Nach Daten sortiert : " + bestellhistorie);

		// bmService.getAllBestellungenADay(bestellhistorie.get(0));

		HashSet<Integer> produkteIds = new HashSet<Integer>();

		for (int i = 0; i < bestellhistorie.size(); i++) {

			for (Integer t : bmService.getAllProdukteADay(bestellhistorie.get(i))) {
				System.out.println("Am " + bestellhistorie.get(i) 
				    + " wurden vom Produkt mit der ID "  + t + ":" + " " 
				    + bmService.getAnzahlProdukt(bestellhistorie.get(i), t) + " Stück bestellt.");

			}


		}
		HashSet<Integer> expected = new HashSet<Integer>();
		expected.add(544);
		assertEquals(expected, bmService.getAnzahlProdukt("01.05.2013", 2));
	}
}
