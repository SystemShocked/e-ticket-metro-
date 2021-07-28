package domain;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;


//Creating table that contains two types of scanners.//
@Entity
@Table(name="scanners")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
		name="type",
		discriminatorType = DiscriminatorType.STRING
		)
public abstract class Scanners {
	//Column with the primary key.//
	@Id
	@Column(name="scannerid")
	@GeneratedValue (strategy = GenerationType.AUTO)
	protected Integer scannerid;
	
	//Column with a special type unique for every scanner.//
    @Column(name="scannerno", length = 15, unique=true)
    protected String scannerno;
	
	//Associations with the tickets and the stations.//
	@OneToMany(orphanRemoval=true, 
            cascade = CascadeType.ALL, 
            mappedBy="scanner", fetch=FetchType.LAZY)
	        private Set<Tickets> tickets = new HashSet<Tickets>();
	
	@ManyToOne(fetch=FetchType.LAZY, 
            cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name="stationid")
    private Stations station;
	
	
	//Constructors//
	public Scanners() {}
	
	public Scanners(String scannerno){
		this.scannerno = scannerno;
	}
	
	//Setters and getters//
	public Integer getScannerid() {
        return scannerid;
	}
	
	public void setScannerno(String scannerno){
		this.scannerno = scannerno;
	}
	
	public String getScannerno(){
		return scannerno;
	}
	
	public Set<Tickets> getTickets(){
		return new HashSet<Tickets>(tickets);
	}
	
	Set<Tickets> friendTickets() {
		return tickets;
	}
	
	public void addTicket(Tickets ticket) {
		if (ticket != null) {
			ticket.setScanner(this);
		}
	}
	
	public void removeTicket(Tickets ticket) {
		if (ticket != null) {
			ticket.setScanner(null);
		}
	}
	
	public void setStation(Stations station) {
		if (this.station != null) {
			this.station.friendScanners().remove(this);
		}
		this.station = station;
		if (this.station != null) {
			this.station.friendScanners().add(this);
		}
	}
	
	public Stations getStation() {
		return station;
	}
		
	
}
