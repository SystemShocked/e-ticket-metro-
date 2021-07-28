package domain;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

import org.eclipse.persistence.internal.oxm.Constants;



//table that contains our employees//
@Entity
@Table(name="Employees")
public class Employee {
	
	//primary key for this table//
	@Id
	@Column(name="Employeeid")
	@GeneratedValue (strategy = GenerationType.AUTO)
	private int employeeid;
	
	//the login required to log in the system//
	@Column(name = "login")
    @Pattern(regexp = Constants.ANY)
    private String login;
	
	//the password required to log in the system//
	@Column(name = "password")
    @Pattern(regexp = Constants.ANY)
    private String password;
	
	//state of the employee//
	@Column(name="authenticated")
	private boolean authenticated;
	
	 //emailaddress//
	 @Column(name="EmailAddress", length = 40, nullable=false)
	 private String emailaddress;
	 
	 
	 //constructors//
	 public Employee () {}
	 
	 public Employee ( String login, String password, String emailaddress){
		 this.login = login;
		 this.password = password;
		 this.emailaddress = emailaddress;
		 this.setAuthenticated(false);
	 }
	 
	 //Setters Getters//
	 public Integer getEmployeeid(){
		 return employeeid;
	 }
	 
	 public void setLogin(String login){
		 this.login = login;
	 }
	 
	 public String getLogin(){
		 return login;
	 }
	 
	 public void setPassword(String password){
		 this.password = password;
	 }
	 
	 public String getPassword(){
		 return password;
	 }
	 
	 public void setEmailAddress(String emailaddress){
	     this.emailaddress = emailaddress;
	 }
	    
	 public String getEmailAddress(){
	     return emailaddress;
	 }
	 
	 public boolean isAuthenticated() {
			return authenticated;
		}

	 public void setAuthenticated(boolean authenticated) {
			this.authenticated = authenticated;
		}

	 
	 
	
	

}
