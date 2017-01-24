package de.hrw.wi.service;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

import de.hrw.wi.persistence.CustomerReadInterface;
import de.hrw.wi.persistence.CustomerWriteInterface;

public class CustomerManagementServiceTest {

	private CustomerManagementServiceInterface cmService;

	@Before
	public void setup() {

		// MockObjekt erstellen

		CustomerReadInterface cmRead = Mockito.mock(CustomerReadInterface.class);
		CustomerWriteInterface cmWrite = Mockito.mock(CustomerWriteInterface.class);

		when(cmRead.getCustomerFname1(0)).thenReturn("Anna");
		when(cmRead.getCustomeLname(0)).thenReturn("Maier");
		when(cmRead.getCustomerDateOfBirth(0)).thenReturn("05.12.1993");
		when(cmRead.getCustomerAdress(0)).thenReturn("Mozartstra�e 1 88250 Weingarten Deutschland");
		when(cmRead.getCustomerMail(0)).thenReturn("anna-maier@gmx.de");
		when(cmRead.getCustomerPhonenumber(0)).thenReturn("+497513366");
		
		when(cmRead.getCustomerFname1(1)).thenReturn("Tobias");
		when(cmRead.getCustomeLname(1)).thenReturn("Eschert");
		when(cmRead.getCustomerDateOfBirth(1)).thenReturn("05.12.1997");
		when(cmRead.getCustomerAdress(1)).thenReturn("Mozartstra�e 5 88250 Weingarten Deutschland");
		when(cmRead.getCustomerMail(1)).thenReturn("teschert@gmx.de");
		when(cmRead.getCustomerPhonenumber(1)).thenReturn("+497514478");
		
		when(cmRead.getCustomerFname1(2)).thenReturn("Mark");
		when(cmRead.getCustomeLname(2)).thenReturn("Schraudolff");
		when(cmRead.getCustomerDateOfBirth(2)).thenReturn("05.12.1990");
		when(cmRead.getCustomerAdress(2)).thenReturn("Mozartstra�e 6 88250 Weingarten Deutschland");
		when(cmRead.getCustomerMail(2)).thenReturn("mark@gmx.de");
		when(cmRead.getCustomerPhonenumber(2)).thenReturn("+497514578");
		
		when(cmRead.getCustomerFname1(3)).thenReturn("Maria");
		when(cmRead.getCustomeLname(3)).thenReturn("Teifel");
		when(cmRead.getCustomerDateOfBirth(3)).thenReturn("04.12.1995");
		when(cmRead.getCustomerAdress(3)).
		    thenReturn("Mozartstra�e 22 88250 Weingarten Deutschland");
		when(cmRead.getCustomerMail(3)).thenReturn("mt@gmx.de");
		when(cmRead.getCustomerPhonenumber(3)).thenReturn("+497513378");
		
		cmService = new CustomerManagementService(cmRead, cmWrite);

	}

	@Test
	public void testGetAllCustomerDataById() {
		
		List<String> customerSet = new ArrayList<String>();
	
		customerSet.add(cmService.getAllCustomerDataById(0).getLname());
		customerSet.add(cmService.getAllCustomerDataById(1).getLname());
		customerSet.add(cmService.getAllCustomerDataById(2).getLname());
		customerSet.add(cmService.getAllCustomerDataById(3).getLname());
		
		
		Collections.sort(customerSet);
		
		System.out.println("Alle Kunden nach Nachname sortiert: " + customerSet);
		
		
		
		for (int i = 0; i < 4; i++) {
			System.out.println("\n Der Kunde mit der IDnummer " + i + " hat folgende Daten: \n"
					+ "\n Vorname: " + cmService.getAllCustomerDataById(i).getFname()
					+ "\n Nachname: " + cmService.getAllCustomerDataById(i).getLname()
					+ "\n Geburtsdatum: " + cmService.getAllCustomerDataById(i).getDateofbirth()
					+ "\n Adresse: " + cmService.getAllCustomerDataById(i).getAdress()
					+ "\n Mail: " + cmService.getAllCustomerDataById(i).getEmail()
					+ "\n Telefon: " + cmService.getAllCustomerDataById(i).getPhonenumber());
		}
		
		
		
		assertEquals("Anna", cmService.getAllCustomerDataById(0).getFname());
		assertEquals("Maier", cmService.getAllCustomerDataById(0).getLname());
		
	}
	@Test
	public void testgetCustomerPhoneById() {
		assertEquals("+497513366", cmService.getCustomerPhoneById(0));
	}
	@Test
	public void testCustomerMailById() {
		assertEquals("anna-maier@gmx.de", cmService.getCustomerMailById(0));
	}
	@Test
	public void testCustomerAdressById() {
		assertEquals("Mozartstra�e 1 88250 Weingarten Deutschland", 
				cmService.getCustomerAdressById(0));
	}
	

	
	
	
	
	
	
	
	
	
}
