package service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


import java.util.List;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domain.Employee;
import persistence.Initializer;
import persistence.JPAUtil;

public class EmployeeServicesTest  {
	
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
	public void testfindAllEmployees() {
		
		EmployeeServices es = new EmployeeServices();
		List<Employee> employees = es.findAllEmployees();

		assertNotNull(employees);
		assertEquals(3, employees.size());
	}
	
	@Test
	public void testFindEmployeeByID(){
		
		EmployeeServices es = new EmployeeServices();
		List<Employee> employees = es.findAllEmployees();
		
		Employee employee = es.findEmployeeById(employees.get(1).getEmployeeid());
		
		assertNotNull("Expected non null from employee", employee);
	}
	
	@Test
	public void testfindEmployeeByLogin(){
		
		
		Employee employee = EmployeeServices.findEmployeeByLogin("petros");
		
		assertNotNull("Expected non null from employee",employee);
		
	}
	
	@Test
	public void testDeleteAnEmployee(){
		
		EmployeeServices es = new EmployeeServices();
		Employee e = EmployeeServices.findEmployeeByLogin("petros");
		es.deleteEmployee(e);
		
		assertNotNull("Expected non null from employee",e);
	}

}
