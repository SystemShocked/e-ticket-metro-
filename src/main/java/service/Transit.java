package service;


import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;

import domain.Tickets;
import persistence.JPAUtil;

//this service implements the transit from the use case//
public class Transit {
	
	private boolean valid;
	EntityManager em;
	TicketServices ts;
	StationServices ss;
	RouteServices rs;
	
	public Transit(){
		em = JPAUtil.getCurrentEntityManager();
		ts = new TicketServices();
		ss = new StationServices(em);
		rs = new RouteServices(em);
		
	}
	
	//The following method tests the validation of provided data for entrance in the platforms//
	public boolean validation(String ticketno, int Charges){
		boolean valid = false;
		List<Tickets> tickets = ts.findAllTickets();
		for(Iterator<Tickets> it = tickets.iterator(); it.hasNext();){
			Tickets ticketTest=it.next();
			if ( ticketTest.getTicketno().equals(ticketno) && ticketTest.getCharges() > 0){
				valid=true;
			    ticketTest.setValid(valid);
				int i=ticketTest.getTicketid();
				Tickets tTest= ts.findTicketsById(i);
				tTest.setValid(true);
				ts.saveOrUpdateTickets(tTest);	
				System.out.println("Your Ticket is valid.");
				break;
			}//end of if
		}//end of for
		if (valid==false) System.out.println("Your Ticket is invalid!");
		return valid;
	}
	
	//the following method tests the provided data for exit from the platform//
	public boolean validationExt(String ticketno, int Charges){
		boolean valid = false;
		List<Tickets> tickets = ts.findAllTickets();
		for(Iterator<Tickets> it = tickets.iterator(); it.hasNext();){
			Tickets ticketTest=it.next();
			if ( ticketTest.getTicketno().equals(ticketno) && ticketTest.getCharges() > 0){
				valid=true;
			    ticketTest.setValid(valid);
				int i=ticketTest.getTicketid();
				Tickets tTest= ts.findTicketsById(i);
				tTest.setValid(true);
				tTest.setCharges(ticketTest.getCharges() - 1);
				ts.saveOrUpdateTickets(tTest);	
				System.out.println("Your Ticket is valid.");
				break;
			}//end of if
		}//end of for
		if (valid==false) System.out.println("Your Ticket is invalid!");
		return valid;
	}	
	
		
}	
