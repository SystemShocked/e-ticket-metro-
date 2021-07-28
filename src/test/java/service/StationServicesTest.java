package service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


import java.util.List;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domain.Stations;
import persistence.Initializer;
import persistence.JPAUtil;

public class StationServicesTest {
	
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
	public void testfindAllStations() {
		
		StationServices ss = new StationServices(em);
		List<Stations> stations = ss.findAllStations();

		assertNotNull(stations);
		assertEquals(3, stations.size());
	}
	
	@Test
	public void testFindStationsById(){
		
		StationServices ss = new StationServices(em);
		List<Stations> stations = ss.findAllStations();
		
		Stations station = ss.findStationsById( stations.get(2).getId());
		
		assertNotNull("Expected non null from station", station);
		
	}
	
	@Test
	public void testFindStationByName(){
		
		StationServices ss = new StationServices(em);
		List<Stations> station =  ss.findStationByName("Peristeri");
		
		assertNotNull("Expected non null station", station);
		
		
	}
	


}
