package service;

import java.util.Iterator;
import java.util.List;


import javax.persistence.EntityManager;

import domain.E_card;
import domain.Tickets;
import persistence.JPAUtil;

public class Recharge {
	private boolean valid;
	EntityManager em;
	TicketServices ts;
	
	public Recharge(){
		em = JPAUtil.getCurrentEntityManager();
		ts = new TicketServices();
	}
	//method that recharges e-tickets and e-cards//
	public boolean recharge(String ticketno){
		boolean valid = false;
		List<Tickets> tickets = ts.findAllTickets();
		for(Iterator<Tickets> it = tickets.iterator(); it.hasNext();){
			Tickets ticketTest=it.next();
			if ( ticketTest.getTicketno().equals(ticketno)){
				valid=true;
			    ticketTest.setValid(valid);
				int i=ticketTest.getTicketid();
				Tickets tTest= ts.findTicketsById(i);
				tTest.setValid(true);
				tTest.setCharges(5);
				ts.saveOrUpdateTickets(tTest);	
				
			}//end of if
		}//end of for
		if (valid==false) System.out.println("Your Ticket is invalid!");
		return valid;
	}
	
	//method that recharges e-cards//
	public boolean rechargeEcard(String ticketno){
		boolean valid = false;
		List<Tickets> tickets = ts.findAllTickets();
		for(Iterator<Tickets> it = tickets.iterator(); it.hasNext();){
			Tickets ticketTest=it.next();
			if ( ticketTest.getTicketno().equals(ticketno)){
				valid=true;
			    ticketTest.setValid(valid);
				int i=ticketTest.getTicketid();
				Tickets tTest= ts.findTicketsById(i);
				tTest.setValid(true);
				((E_card)tTest).setDuration(10);
				ts.saveOrUpdateTickets(tTest);	
		
			}//end of if
		}//end of for
		if (valid==false) System.out.println("Your Ticket is invalid!");
		return valid;
	}

}
