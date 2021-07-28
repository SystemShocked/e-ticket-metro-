package resource;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.spi.TestContainerFactory;

import domain.Stations;
import domain.Routes;
import persistence.Initializer;
import persistence.JPAUtil;
import service.StationServices;

public class MetroResourceTest extends JerseyTest{
	
	Initializer dataHelper;
	
	public MetroResourceTest() {
		super();
	}
	
	public MetroResourceTest(TestContainerFactory testContainerFactory) {
		super(testContainerFactory);
	}
	
	public MetroResourceTest(Application jaxrsApplication) {
		super(jaxrsApplication);
	}
	
	@Override
	public void setUp() throws Exception {
		super.setUp();
		dataHelper = new Initializer();
		dataHelper.prepareData();
	}
	
	public List<Stations> findStationsByName(String stationname) {
		EntityManager em = JPAUtil.getCurrentEntityManager();
		
		StationServices stationService = new StationServices(em);
		List<Stations> stations = stationService.findStationByName(stationname);
		
		em.close();
		return stations;
		
	}
	
	public List<Stations> listStations() {
		
		EntityManager em = JPAUtil.getCurrentEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		List<Stations> stations = em.createQuery("select s from Stations s").getResultList();
		
		tx.commit();
		em.close();
		return stations;
	}
	
	public List<Routes> listRoutes() {
		EntityManager em = JPAUtil.getCurrentEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		List<Routes> routes = em.createQuery("select r from Routes r").getResultList();
		
		tx.commit();
		em.close();
		return routes;
	}

}
























