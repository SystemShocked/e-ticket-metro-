package service;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import persistence.Initializer;
import persistence.JPAUtil;

public class AuthenticationTest {
	
	Initializer dataHelper;
	protected EntityManager em;
	
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
	
	//test if employee logs in//
	@Test
	public void testIfEmployeeLogsIn(){
		Authentication a1 = new Authentication();
		a1.requestToLogin("dimitra", "ArcticMonkeys");
		boolean flag = EmployeeServices.findEmployeeByLogin("dimitra").isAuthenticated();
		Assert.assertTrue(flag);
	}
	
	//test if a non authenticated / unknown employee is rejected//
	@Test
	public void testIfEmployeeIsRejected(){
		Authentication a1 = new Authentication();
		boolean flag = a1.requestToLogin("marios", "qwerty");
		Assert.assertFalse(flag);
		
	}

}
