package service;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityTransaction;


import domain.Tickets;
import persistence.JPAUtil;

public class TicketServices {
	static EntityManager em;
	
	public TicketServices(){
		em = JPAUtil.getCurrentEntityManager();
	}
	
	//update tickets at database//
	public boolean saveOrUpdateTickets(Tickets t) {

		if (t != null) {
			em.merge(t);
			return true;
		}

		return false;
	}
	
	//find a ticket using its PK//
	public  Tickets findTicketsById(int id){
		return em.find(Tickets.class, id);
	}
	
	//find a ticket using its ticket number//
	public Tickets findTicketByNo(String ticketno){
		EntityTransaction tx = em.getTransaction();
		tx.begin();
	       Tickets ticket = (Tickets) em.createQuery("select t from Tickets t where t.ticketno like :ticketno")
	    		              .setParameter("ticketno", ticketno).getSingleResult();
	    tx.commit();
		return ticket;
	}
	
	//remove a ticket from the database//
	public boolean deleteTicket(int ticketid) {
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		try {
			Tickets ticket = em.getReference(Tickets.class, ticketid);
			em.remove(ticket);
		} catch (EntityNotFoundException e) {
			tx.rollback();
			return false;
		}

		tx.commit();

		return true;

	}
	
	public boolean deleteTicket(Tickets t) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		if (t != null) {
			em.remove(t);
			return true;
		}
		tx.commit();
		return false;

	}
	
	//find all tickets//
	@SuppressWarnings("unchecked")
	public List<Tickets> findAllTickets() {

		EntityTransaction tx = em.getTransaction();
		tx.begin();
		List<Tickets> results = null;

		results = em.createQuery("select t from Tickets t").getResultList();

		tx.commit();
		return results;
	}
	
	

}
