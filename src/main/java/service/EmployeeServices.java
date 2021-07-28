package service;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.EntityManager;

import domain.Employee;
import persistence.JPAUtil;

public class EmployeeServices {
    static EntityManager em;
	
	public EmployeeServices(){
		em = JPAUtil.getCurrentEntityManager();
	}
	
	//update Employee at database//
	public boolean saveOrUpdateEmployee(Employee e) {

		if (e != null) {
			em.merge(e);
			return true;
		}

		return false;
	}
	
	//find employee using this id//
	public  Employee findEmployeeById(int id){
		return em.find(Employee.class, id);
	}
	
	//Use the login to search the database for the Employee//
	@SuppressWarnings("unchecked")
	public static Employee findEmployeeByLogin(String login) {
		EntityManager em = JPAUtil.getCurrentEntityManager();
		Query query = em.createQuery("select e from Employee e where e.login like :loginCrit");
		query.setParameter("loginCrit", login);
		List<Employee> resultsEmployees = query.getResultList();
		if (!resultsEmployees.isEmpty())
			return  resultsEmployees.get(0);
		else
			return null;
	}
	
	//return all the Employees from the database//
	public List<Employee> findAllEmployees() {
		List<Employee> allEmployees = null;

		allEmployees = em.createQuery("select e from Employee e", Employee.class).getResultList();

		return allEmployees;
	}
	
	//remove Employee from the database//
	public boolean deleteEmployee(Employee a) {

		if (a != null) {
			em.remove(a);
			return true;
		}

		return false;
	}
	
	

}
