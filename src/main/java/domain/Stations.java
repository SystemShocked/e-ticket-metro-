package domain;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;


//Creating a table that contains the stations.//
@Entity
@Table(name="stations")
public class Stations {
	
	//Primary key for the table.//
	@Id 
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	
	//Column containing the name of the station.//
	@Column(name="stationname", length = 50, nullable = false)
	private String stationname;
	
	//Column that contains the overcrowding of the stations.//
	@Column(name="overcrowding", length = 20, nullable = false)
	private Integer overcrowding;
	
	//Associations with routes and scanners.//
	@ManyToOne(fetch=FetchType.LAZY, 
            cascade = { CascadeType.PERSIST, CascadeType.MERGE }
	        )
    @JoinColumn(name="routeId")
    private Routes route;
	
	@OneToMany(orphanRemoval=true, 
            cascade = CascadeType.ALL, 
            mappedBy="station", fetch=FetchType.LAZY)  
    private Set<Scanners> scanners = new HashSet<Scanners>();
	
	//Constructors//
    public Stations () {}
	
	public Stations(String stationname, int overcrowding){
		this.stationname = stationname;
		this.overcrowding = overcrowding;
	}
	
	//Setters Getters//
	public Integer getId() {
        return id;
	}
	
	public void setStationName(String stationname){
		this.stationname = stationname;
	}
	
	public String getStationName(){
		return stationname;
	}
	
	public void setOvercrowding(int overcrowding){
		this.overcrowding = overcrowding;
	}
	
	public int getOvercrowding(){
		return overcrowding;
	}
	
	public void setRoute(Routes route) {
		this.route = route;
	}
	
	public Routes getRoute() {
		return route;
	}
	
	public Set<Scanners> getScanners() {
		return new HashSet<Scanners>(scanners);
	}
	
	Set<Scanners> friendScanners() {
		return scanners;
	}
	
	public void addScanner(Scanners scanner) {
		if (scanner != null) {
			scanner.setStation(this);
		}
	}
	
	public void removeScanner(Scanners scanner) {
		if (scanner != null) {
			scanner.setStation(null);
		}
	}

}
