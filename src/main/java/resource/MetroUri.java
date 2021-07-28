package resource;

public class MetroUri {
	
	public static final String STATIONS = "stations";
	
	public static final String ROUTES = "routes";
	
	public static final String STATION_SEARCH = "stations/search";
	
	public static final String ROUTE_SEARCH = "routes/search";
	
	public static String stationIdUri(String id) {
		return STATIONS + "/" + id;
	}
	
	public static String stationSearchUri(String stationname) {
		return STATION_SEARCH + "?stationname=" + stationname;
	}
	
	public static String routeSearchUri(String routename) {
		return ROUTE_SEARCH + "?routename=" + routename;
	}

}

