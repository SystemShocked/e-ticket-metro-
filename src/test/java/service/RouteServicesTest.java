package service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


import java.util.List;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domain.Routes;
import persistence.Initializer;
import persistence.JPAUtil;

public class RouteServicesTest {
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
	public void testFindAllRoutes(){
		RouteServices rs = new RouteServices(em);
		List<Routes> routes = rs.findAllRoutes();

		assertNotNull(routes);
		assertEquals(3, routes.size());
	}
	
	@Test
	public void testFindRoutesById(){
		RouteServices rs = new RouteServices(em);
		List<Routes> routes = rs.findAllRoutes();
		
		Routes route = rs.findRoutesById( routes.get(2).getId());
		
		assertNotNull("Expected non null from route", route);
	}
	
	@Test
	public void testFindRouteByName(){
		RouteServices rs = new RouteServices(em);
		List<Routes> route =  rs.findRouteByName("redline");
		
		assertNotNull("Expected non null route", route);
	}
	
}
