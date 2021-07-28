package service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


import java.util.List;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domain.Tickets;
import persistence.Initializer;
import persistence.JPAUtil;

public class TicketServicesTest {
	
	Initializer dataHelper;
	EntityManager em;
	
	@Before
	public final void setUp() {
		
		dataHelper = new Initializer();
		dataHelper.prepareData();
		em = JPAUtil.getCurrentEntityManager();
	}
	
	@After
	public final void tearDown() {
		em.close();
		
	}
	
	@Test
	public void testfindAllTickets() {
		
		TicketServices ts = new TicketServices();
		List<Tickets> tickets = ts.findAllTickets();

		assertNotNull(tickets);
		assertEquals(6, tickets.size());
	}
	
	@Test
	public void testFindTicketsById(){
		
		TicketServices ts = new TicketServices();
		List<Tickets> tickets = ts.findAllTickets();
		
		Tickets ticket = ts.findTicketsById( tickets.get(2).getTicketid());
		
		assertNotNull("Expected non null from ticket", ticket);
		
	}
	
	@Test
	public void testFindTicketByNo(){
		
		TicketServices ts = new TicketServices();
		Tickets ticket =  ts.findTicketByNo("AA12");
		
		assertNotNull("Expected non null ticket", ticket);
		
		
	}
	
	@Test
	public void testDeleteTicket(){
		
		TicketServices ts = new TicketServices();
		Tickets ticket =  ts.findTicketByNo("ww99");
		ts.deleteTicket(ticket);
		
		assertNotNull("Expected non null from ticket", ticket);
		
	}

}
