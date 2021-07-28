package service;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import persistence.Initializer;
import persistence.JPAUtil;

public class TransitTest {
	
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
	public void testValidation(){
		TicketServices ts = new TicketServices();
		Transit t1 = new Transit();
		t1.validation("B52", 1);
		boolean flag = ts.findTicketByNo("B52").isValid();
		Assert.assertTrue(flag);
	}
	
	@Test
	public void testRejection(){
		TicketServices ts = new TicketServices();
		Transit t1 = new Transit();
		ts.findTicketByNo("ww99").setCharges(0);
		boolean flag = t1.validation("ww99", 0);
		Assert.assertFalse(flag);
		
	}
	
	@Test
	public void testValidationExt(){
		TicketServices ts = new TicketServices();
		Transit t1 = new Transit();
		t1.validationExt("B52", 1);
		boolean flag = ts.findTicketByNo("B52").isValid();
		int charges = ts.findTicketByNo("B52").getCharges();
		Assert.assertTrue(flag);
		assertEquals(0, charges);
				
	}
	
	@Test
	public void testRejectionExt(){
		TicketServices ts = new TicketServices();
		Transit t1 = new Transit();
		ts.findTicketByNo("ww99").setCharges(0);
		boolean flag = t1.validationExt("ww99", 0);
		Assert.assertFalse(flag);
		
	}

}
