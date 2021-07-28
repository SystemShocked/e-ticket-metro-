package resource;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.xml.bind.annotation.XmlRootElement;

import domain.Routes;

@XmlRootElement
public class RouteInfo {

	private Integer id;
	
	private String routename;
	
	private Integer fullness;
	
	
	public RouteInfo() {
		
	}
	
	public RouteInfo(int id, String routename, int fullness) {
		this(routename, fullness);
		this.id = id;
	}
	
	
	public RouteInfo(String routename, int fullness) {
		super();
		this.routename = routename;
		this.fullness = fullness;
	}
	
	public RouteInfo(Routes routes) {
		id = routes.getId();
		routename = routes.getRouteName();
		fullness = routes.getFullness();
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getRoutename() {
		return routename;
	}
	
	public void setRoutename(String routename) {
		this.routename = routename;
	}
	
	public Integer getFullness() {
		return fullness;
	}
	
	public void setFullness(Integer fullness) {
		this.fullness = fullness;
	}
	
	public static RouteInfo wrap(Routes r) {
		return new RouteInfo(r);
	}
	
	public static List<RouteInfo> wrap (List<Routes> routes) {
		
		List<RouteInfo> routeInfoList = new ArrayList<> ();
		
		for (Routes r : routes) {
			routeInfoList.add(new RouteInfo(r));
		}
		
		return routeInfoList;
	}
	
	public Routes getRoute(EntityManager em) {
		
		Routes route = null;
		
		if (id != null) {
			route = em.find(Routes.class, id);
		}else {
			route = new Routes();
		}
		
		route.setRouteName(routename);
		route.setFullness(fullness);
		
		return route;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
