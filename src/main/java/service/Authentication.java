package service;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;

import domain.Employee;
import persistence.JPAUtil;


//This Service implements the Login In the Use case//
public class Authentication {
	
	private boolean successfullLogin;
	EntityManager em;
	EmployeeServices es;
	
	
	public Authentication() {
		em = JPAUtil.getCurrentEntityManager();
		
		es=new EmployeeServices();
	}
	
	//The following method test the validation of provided data in order to log in the system//
	/**
	 * @param login, the inserted login for authentication
	 * @param password, the provided password
	 * @return
	 */
	public boolean requestToLogin(String login,String password){
		boolean successfullLogin=false;
		List <Employee> employeesWhoWork= es.findAllEmployees();
		/***For every employee find if the provided login & password 
		 * match with the data stored in our database with all the employees***/
		for(Iterator<Employee> it = employeesWhoWork.iterator(); it.hasNext();){
			Employee employeeTest=it.next();
			if ( employeeTest.getLogin().equals(login) && employeeTest.getPassword().equals(password)){
				successfullLogin=true;
			    employeeTest.setAuthenticated(successfullLogin);
				int i=employeeTest.getEmployeeid();
				Employee eTest= es.findEmployeeById(i);
				eTest.setAuthenticated(true);
				es.saveOrUpdateEmployee(eTest);	
				System.out.println("Hey "+login+"! You logged in successfully.");
				break;
			}//end of if
		}//end of for
		if (successfullLogin==false) System.out.println("No employee was found with this login");
		return successfullLogin;
	}

}
