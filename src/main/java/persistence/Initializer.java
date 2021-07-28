package persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import persistence.JPAUtil;
import domain.E_card;
import domain.E_ticket;
import domain.EntScanners;
import domain.ExtScanners;
import domain.Routes;
import domain.Stations;
import domain.Passengers;
import domain.Employee;


public class Initializer {
	
	
	public void  eraseData() {
        EntityManager em = JPAUtil.getCurrentEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        
        Query query = em.createNativeQuery("delete from tickets");
        query.executeUpdate();
        query = em.createNativeQuery("delete from scanners");
        query.executeUpdate();                
        query = em.createNativeQuery("delete from routes");
        query.executeUpdate();         
        query = em.createNativeQuery("delete from stations");
        query.executeUpdate();
        query = em.createNativeQuery("delete from Employees");
        query.executeUpdate();
        query = em.createNativeQuery("delete from Passengers");
        query.executeUpdate();
        
        em.flush();
        tx.commit();
    }
	
	public void prepareData() {
		eraseData();
		
		//Initialize Employees//
		Employee nikos = new Employee("nikos", "olympiakos","nikos7@gmail.com");
		Employee petros = new Employee("petros", "pana8a", "petros13@gmail.com");
		Employee dimitra = new Employee("dimitra", "ArcticMonkeys", "dimitra17@gmail.com");
		dimitra.setAuthenticated(true);
		
		//Initialize Passengers//
		Passengers mixalhs = new Passengers("mixalhs", "papadopoulos", "mixpap@gmail.com");
		Passengers gewrgia = new Passengers("gewrgia", "kokalh", "gewr96@gamil.com");
		Passengers dio = new Passengers("dio", "delmonto", "dio95@gmail.com");
		
		//Initialize E_Tickets//
		E_ticket ticket1 = new E_ticket("A91",1);
		E_ticket ticket2 = new E_ticket("B52",1);
		E_ticket ticket3 = new E_ticket("P42",0);
		
		//Initialize E_cards//
		E_card card1 = new E_card("AA12",1,0);
		E_card card2 = new E_card("ww99",0,5);
		E_card card3 = new E_card("QW30",0,30);
		
		//Initialize entscanners//
		EntScanners scanner1 = new EntScanners("A25S");
		EntScanners scanner2 = new EntScanners("Q58P");
		EntScanners scanner3 = new EntScanners("POP3");
		
		//Initialize extscanners//
		ExtScanners scanner4 = new ExtScanners("LDO00");
		ExtScanners scanner5 = new ExtScanners("FF20");
		ExtScanners scanner6 = new ExtScanners("TT100");
		
		//Initialize Stations//
		Stations station1 = new Stations("Peristeri", 0);
		Stations station2 = new Stations("Kirki", 0);
		Stations station3 = new Stations("Kwlonaki", 0);
		station3.setOvercrowding(14);
		station2.setOvercrowding(11);
		
		//Initialize Routes//
		Routes route1 = new Routes("redline", 0);
		Routes route2 = new Routes("yellowline", 0);
		Routes route3 = new Routes("blueline", 0);
		route2.setFullness(25);
		
		
		//upload the data//
		EntityManager em = JPAUtil.getCurrentEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        
        em.persist(nikos);
        em.persist(petros);
        em.persist(dimitra);
        
        em.persist(mixalhs);
        em.persist(gewrgia);
        em.persist(dio);
        
        em.persist(ticket1);
        em.persist(ticket2);
        em.persist(ticket3);
        
        em.persist(card1);
        em.persist(card2);
        em.persist(card3);
        
        em.persist(scanner1);
        em.persist(scanner2);
        em.persist(scanner3);
        
        em.persist(scanner4);
        em.persist(scanner5);
        em.persist(scanner6);
        
        em.persist(station1);
        em.persist(station2);
        em.persist(station3);
        
        em.persist(route1);
        em.persist(route2);
        em.persist(route3);
        
        tx.commit();
		
				
    }
}

