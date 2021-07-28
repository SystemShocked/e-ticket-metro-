package domain;


import javax.persistence.*;

//Creating a table that contains two types of tickets.//
@Entity
@Table(name = "tickets")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
		name="type",
		discriminatorType = DiscriminatorType.STRING
		)
public abstract class Tickets {
	//This column contains the primary key of the table.//
	@Id
	@Column(name="ticketid")
	@GeneratedValue (strategy = GenerationType.AUTO)
	protected Integer ticketid; 
	
	//This column contains a special string unique for every ticket.//
    @Column(name="ticketno", length = 20, unique=true)
    protected String ticketno;
	
	//This column describes how many charges the ticket has.//
	@Column(name="Charges", nullable=false)
	protected int charges;
	
	//This column describes if a ticket is valid//
	@Column(name="Valid", nullable=false)
	protected boolean valid;
	
	//Tickets are associated with scanners by many to one.//
	@ManyToOne(fetch=FetchType.LAZY, 
            cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name="scannerid")
	private Scanners scanner;
	
	//Constructors//
	public Tickets (){}
	
	
	public Tickets (String ticketno, int charges){
		this.ticketno=ticketno;
		this.charges=1;
	}
	
	//Sets and Gets for the class.//
	public Integer getTicketid() {
        return ticketid;
    }
	
	public void setTicketno (String ticketno){
		this.ticketno = ticketno;
	}
	
	public String getTicketno(){
		return ticketno;
	}
	
	public void setCharges(int charges){
		this.charges = charges;
	}
	
	public int getCharges(){
		return charges;
	}
	
	public boolean isValid(){
		return valid;
	}
	
	public void setValid(boolean valid){
		this.valid = valid;
	}
	
	public void setScanner(Scanners scanner) {
		if (this.scanner != null) {
			this.scanner.friendTickets().remove(this);
		}
		this.scanner = scanner;
		if (this.scanner != null) {
			this.scanner.friendTickets().add(this);
		}
	}
	
	public Scanners getScanner() {
		return scanner;
	}
	
	

	
	
}
