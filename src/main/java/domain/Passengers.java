package domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;



//Creating a table containing passengers that have an E_card//
@Entity
@Table(name="Passengers")
public class Passengers {
	
	//Primary key of the table//
	@Id
	@Column(name="passengerid")
	@GeneratedValue (strategy = GenerationType.AUTO)
	private int passengerid;
	
	//name of the passenger//
	 @Column(name="firstname", length=50, nullable = false)
	 private String firstName;
	 
	 //lastname of the passenger//
	 @Column(name="lastname", length=50, nullable = false)
	 private String lastName;
	 
	//emailaddress of the passenger//
	 @Column(name="EmailAddress", length = 40, nullable=false)
	 private String emailaddress;
	 
	 //the passengers are associated with E_cards by OneToOne//
	 @OneToMany(orphanRemoval=true, 
	            cascade = CascadeType.ALL, 
	            mappedBy="passenger", fetch=FetchType.LAZY) 
	 private  Set<E_card> ecards = new HashSet<E_card>();
	 
	 
	 //constructors//
	 public Passengers() { }
	 
	 public Passengers(String firstName, String lastName, String emailaddress) {
	        this.firstName = firstName;
	        this.lastName = lastName;
	        this.emailaddress = emailaddress;
	    }
	 
	 //Setters Getters//
	 public Integer getPassengerId(){
		 return passengerid;
	 }
	 
	 public void setFirstName(String firstName) {
	        this.firstName = firstName;
	    }

	
	    public String getFirstName() {
	        return firstName;
	    }

	    
	    public void setLastName(String lastName) {
	        this.lastName = lastName;
	    }

	   
	    public String getLastName() {
	        return lastName;
	    }
	    
	    
	    public void setEmailAddress(String emailaddress){
	    	this.emailaddress = emailaddress;
	    }
	    
	    public String getEmailAddress(){
	    	return emailaddress;
	    }


}
