package de.hrw.wi.service;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;


import org.junit.Before;
import org.junit.Test;


import de.hrw.wi.persistence.CustomerReadInterface;
import de.hrw.wi.persistence.CustomerWriteInterface;
import de.hrw.wi.persistence.CustomerRealDatabase;


public class CustomerManagementServiceIntegrationTest {

	private CustomerManagementServiceInterface cmService;
	
	
	@Before
	public void setUp() {
		//Create Real Database
		CustomerReadInterface dbRead = new CustomerRealDatabase();
		CustomerWriteInterface dbWrite = new CustomerRealDatabase();
		cmService = new CustomerManagementService(dbRead, dbWrite);
	}
	
	@Test
	public void testGetAllCustomerDataById() throws SQLException {
		
		cmService.addCustomer(0, "Anna", "Maier", "Testadresse", 
				"+4975815244664", "03.03.1990", "beispiel@gmx.de");
		
		System.out.println(cmService.getAllCustomerDataById(0).getFname());
		System.out.println(cmService.getAllCustomerDataById(0).getLname());
		System.out.println(cmService.getAllCustomerDataById(0).getAdress());
		System.out.println(cmService.getAllCustomerDataById(0).getPhonenumber());
		System.out.println(cmService.getAllCustomerDataById(0).getDateofbirth());
		System.out.println(cmService.getAllCustomerDataById(0).getEmail());
		
		assertEquals("Anna", cmService.getAllCustomerDataById(0).getFname());
		assertEquals("Maier", cmService.getAllCustomerDataById(0).getLname());
		assertEquals("Testadresse", cmService.getAllCustomerDataById(0).getAdress());
		assertEquals("+4975815244664", cmService.getAllCustomerDataById(0).getPhonenumber());
		assertEquals("03.03.1990", cmService.getAllCustomerDataById(0).getDateofbirth());
		assertEquals("beispiel@gmx.de", cmService.getAllCustomerDataById(0).getEmail());
		
		cmService.deleteCustomerById(0);
		
	}
	
	@Test
	public void testGetCustomerPhoneById() throws SQLException {
		cmService.addCustomer(1, "Alia", "Serefsiz", "Testadresse", 
				"+4975815244664", "03.03.1990", "beispiel@gmx.de");
		
		assertEquals("+4975815244664", cmService.getCustomerPhoneById(1));
		
		cmService.deleteCustomerById(1);
	}
	
	@Test
	public void testGetCustomerMailById() throws SQLException {
		cmService.addCustomer(2, "Alia", "Serefsiz", "Testadresse", 
				"+4975815244664", "03.03.1990", "beispiel@gmx.de");
		
		assertEquals("beispiel@gmx.de", cmService.getCustomerMailById(2));
		
		cmService.deleteCustomerById(2);
	}
	
	@Test
	public void testGetCustomerAdressById() throws SQLException {
		cmService.addCustomer(3, "Alia", "Serefsiz", "Testadresse", 
				"+4975815244664", "03.03.1990", "beispiel@gmx.de");
		
		assertEquals("Testadresse", cmService.getCustomerAdressById(3));
		
		cmService.deleteCustomerById(3);
	}

}
