package service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


import java.util.List;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import persistence.Initializer;
import persistence.JPAUtil;

public class StatisticsTest {
	
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
	public void testStatistics(){
		Statistics s = new Statistics();
		List<Statistics> statistics = s.getStatistics(1);
		
		assertNotNull(statistics);
		assertEquals(0, statistics.size());
		
	}

}
