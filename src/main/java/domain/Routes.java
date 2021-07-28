package domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

//Creating table routes.//
@Entity
@Table(name="routes")
public class Routes {
	
	//Primary key for the table.//
	@Id 
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	
	//Column containing the name of the route.//
	@Column(name="routename", length = 30, nullable=false)
	private String routename;
	
	//Column containing the fullness of the route.//
	@Column(name="fullness", length = 20, nullable=false)
	private Integer fullness;
	
	//Associations with stations.//
	@OneToMany(orphanRemoval=true, 
            cascade = CascadeType.ALL, 
            mappedBy="route", fetch=FetchType.LAZY)    
    private Set<Stations> stations = new HashSet<Stations>();
	
	//Constructors//
	public Routes () {}
	
	public Routes(String routename, int fullness){
		this.routename = routename;
		this.fullness = fullness;
	}
	
	//Setters Getters//
	public Integer getId() {
        return id;
	}
	
	public void setRouteName(String routename){
		this.routename = routename;
	}
	
	public String getRouteName(){
		return routename;
	}
	
	public void setFullness(int fullness){
		this.fullness = fullness;
	}
	
	public int getFullness() {
		return fullness;
	}

	public  Set<Stations> getStations() {
		return new HashSet<Stations>(stations);
	}
	
	Set<Stations> friendStations() {
		return stations;
	}
	
	public void addStation(Stations station) {
		if (station != null) {
			station.setRoute(this);
		}
	}
	
	public void removeStation(Stations station) {
		if (station != null) {
			station.setRoute(null);
		}
	}
	

}
