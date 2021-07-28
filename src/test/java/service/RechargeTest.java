package service;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import domain.E_card;
import persistence.Initializer;
import persistence.JPAUtil;

public class RechargeTest {
	
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
	public void testRechargeByFive(){
		TicketServices ts = new TicketServices();
		Recharge r1 = new Recharge();
		boolean flag = r1.recharge("QW30");
		int charges = ts.findTicketByNo("QW30").getCharges();
		Assert.assertTrue(flag);
		assertEquals(5, charges);
	}
	
	@Test
	public void testRechargeInvalidTicket(){
		Recharge r1 = new Recharge();
		boolean flag = r1.recharge("poe");
		Assert.assertFalse(flag);
		
	}
	
	@Test
	public void testRechargeEcardDurationTen(){
		TicketServices ts = new TicketServices();
		Recharge r1 = new Recharge();
		boolean flag = r1.rechargeEcard("AA12");
		int duration = ((E_card)ts.findTicketByNo("AA12")).getDuration();
		Assert.assertTrue(flag);
		assertEquals(10, duration);
	}
	
	@Test(expected = ClassCastException.class)
	public void testRechargeEticketReject(){
		Recharge r1 = new Recharge();
		r1.rechargeEcard("A91");
		
	}
	
	@Test
	public void testRechargeInvalidEcard(){
		Recharge r1 = new Recharge();
		boolean flag = r1.rechargeEcard("meow");
		Assert.assertFalse(flag);
		
	}
	


}
