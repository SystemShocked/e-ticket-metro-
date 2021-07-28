package service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityTransaction;

import domain.Stations;

public class StationServices {
    EntityManager em;
	
	public StationServices(EntityManager em){
		this.em = em;
	}
	
	public Stations save(Stations station) {
		
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		if (station.getId() != null) {
			station = em.merge(station);
		}else {
			em.persist(station);
		}
		tx.commit();
		return station;
		
	}
	
	
	//update stations at database//
	public boolean saveOrUpdateStations(Stations s) {

		if (s != null) {
			em.merge(s);
			return true;
		}

		return false;
	}
	
	//find a station using its PK//
	public  Stations findStationsById(int id){
		return em.find(Stations.class, id);
	}
	
	//find a station using its name//
	@SuppressWarnings("unchecked")
	public List<Stations> findStationByName(String stationname){
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		List<Stations> results = null;
		
		   results = em.createQuery("select s from Stations s where s.stationname like :stationname")
				                           .setParameter("stationname", "%" + stationname + "%").getResultList();		
		tx.commit();
		return results;
	}
	
	//remove a station from the database//

	public boolean deleteStation(Stations s) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		if (s != null) {
			em.remove(s);
			return true;
		}
		tx.commit();
		return false;

	}
	
	public boolean deleteStation(int Id) {
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
			Stations station = em.getReference(Stations.class, Id);
			em.remove(station);
		}catch (EntityNotFoundException e) {
			tx.rollback();
			return false;
		}
		
		tx.commit();
		return true;
		
	}
	
	//find all Stations//
	@SuppressWarnings("unchecked")
	public List<Stations> findAllStations() {

		EntityTransaction tx = em.getTransaction();
		tx.begin();
		List<Stations> results = null;

		results = em.createQuery("select s from Stations s").getResultList();

		tx.commit();
		return results;
	}

}
