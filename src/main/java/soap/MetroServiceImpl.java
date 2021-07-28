package soap;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;
import javax.persistence.EntityManager;

import domain.Stations;
import persistence.JPAUtil;

@WebService(endpointInterface = "domain.MetroService")
public class MetroServiceImpl implements MetroService{
	
	@SuppressWarnings("unchecked")
	public List<StationInfo> getAllStations() {
		EntityManager em = JPAUtil.createEntityManager();
		List<StationInfo> result = new ArrayList<StationInfo>();
		List<Stations> stations = em.createQuery("select s from Stations s").getResultList();
		for (Stations station : stations) {
			result.add(new StationInfo(station));
		}
		em.close();
		return result;
	}
	
	public StationInfo getStationInfo(String stationname) {
		EntityManager em = JPAUtil.createEntityManager();
		Stations station = em.find(Stations.class, stationname);
		StationInfo stationInfo = new StationInfo(station);
		em.close();
		return stationInfo;
	}



}
