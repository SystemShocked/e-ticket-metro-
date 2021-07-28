package soap;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import domain.Stations;
import domain.Routes;

@XmlType(name="StationInfo")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class StationInfo {
	
	private Integer id;
	
	private String stationname;
	
	private Integer overcrowding;
	
	private int routeId;
	
	public StationInfo() {
		
	}
	
	public StationInfo(int id, String stationname, int overcrowding, int routeId) {
		this(stationname, overcrowding, routeId);
		this.id = id;
	}
	
	public StationInfo(String stationname, int overcrowding, int routeId) {
		super();
		this.stationname = stationname;
		this.overcrowding = overcrowding;
		this.routeId = routeId;
	}
	
	public StationInfo(Stations station) {
		id = station.getId();
		stationname = station.getStationName();
		overcrowding = station.getOvercrowding();
		routeId = station.getRoute().getId();
		
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
	
	public void setRouteId(int routeId) {
		this.routeId=routeId;
	}
	
	public int getRouteId() {
		return routeId;
	}
	
	public static StationInfo wrap (Stations s) {
		return new StationInfo(s);
	}
	
	public static List<StationInfo> wrap(List<Stations> stations) {
		
		List<StationInfo> stationInfoList = new ArrayList<>();
		
		for (Stations s : stations) {
			stationInfoList.add(new StationInfo(s));
		}
		
		return stationInfoList;
	}
	
	public Stations getStation(EntityManager em) {
		
		Stations station = null;
		
		if(id != null) {
			station = em.find(Stations.class,id);
		}else {
			station = new Stations();
		}
		
		station.setStationName(stationname);
		station.setOvercrowding(overcrowding);
		
		Routes route = em.getReference(Routes.class, id);
		station.setRoute(route);
		
		return station;
	}
	

}



















