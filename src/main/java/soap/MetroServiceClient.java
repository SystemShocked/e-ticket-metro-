package soap;

import java.net.URL;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class MetroServiceClient {
	public static void main(String args[ ]) throws Exception {
		URL url = new URL("http://localhost:9998/metro?wsdl");
		
		QName qname = new QName("http://ws.metro.com/", "MetroServiceImplService");
		
		Service service = Service.create(url, qname);
		
		MetroService endpointInterface = service.getPort(MetroService.class);
		
		
		// Λήψη και εμφάνιση όλων των Σταθμών
		List<StationInfo> stations = endpointInterface.getAllStations();
		for (StationInfo station : stations) {
			System.out.println("Station id: " + station.getId());
			System.out.println("Station name: " + station.getStationName());
			System.out.println("Station overcrowding: " + station.getOvercrowding());
			System.out.println("Route id: " + station.getRouteId());
		}
		
		endpointInterface.getStationInfo("Kirki");
		
		
	}

}
