package service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityTransaction;

import domain.Routes;

public class RouteServices {
    EntityManager em;
	
	public RouteServices(EntityManager em){
		this.em = em;
	}
	
	public Routes save(Routes route) {
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		if (route.getId() != null) {
			route = em.merge(route);	
		}else {
			em.persist(route);
		}
		tx.commit();
		return route;
	}
	
	
	//update routes at database//
	public boolean saveOrUpdateRoutes(Routes r) {

		if (r != null) {
			em.merge(r);
			return true;
		}

		return false;
	}
	
	//find a route using its PK//
	public  Routes findRoutesById(int id){
		return em.find(Routes.class, id);
	}
	
	//find a route using its name//
	@SuppressWarnings("unchecked")
	public List<Routes> findRouteByName(String routename){
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		   List<Routes> results = null;
		
		   results = em.createQuery("select r from Routes r where r.routename like :routename")
				                  .setParameter("routename", "%" + routename + "%").getResultList();		
		tx.commit();
		return results;
		
	}
	
	//remove a route from the database//
	public boolean deleteRoute(int Id) {
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		try {
			Routes route = em.getReference(Routes.class, Id);
			em.remove(route);
		} catch (EntityNotFoundException e) {
			tx.rollback();
			return false;
		}

		tx.commit();

		return true;

	}
	
	public boolean deleteRoute(Routes r) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		if (r != null) {
			em.remove(r);
			return true;
		}
		tx.commit();
		return false;

	}
	
	//find all routes//
	@SuppressWarnings("unchecked")
	public List<Routes> findAllRoutes() {

		EntityTransaction tx = em.getTransaction();
		tx.begin();
		List<Routes> results = null;

		results = em.createQuery("select r from Routes r").getResultList();

		tx.commit();
		return results;
	}
	
	

}
