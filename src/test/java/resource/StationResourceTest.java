package resource;

import static resource.MetroUri.STATIONS;
import static resource.MetroUri.STATION_SEARCH;
import static resource.MetroUri.stationIdUri;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.server.ResourceConfig;
import org.junit.Assert;
import org.junit.Test;

import resource.DebugExceptionMapper;
import soap.StationInfo;
import domain.Stations;
import domain.Routes;

public class StationResourceTest extends MetroResourceTest{
	
	@Override
	protected Application configure() {
		return new ResourceConfig(StationResource.class, DebugExceptionMapper.class);
	}
	
	@Test
	public void testListStationById() {
		
		List<StationInfo> stations = target(STATIONS).request().get(new GenericType<List<StationInfo>> () {
			
		});
		
		String firstStationId = Integer.toString(stations.get(0).getId());
		
		StationInfo station = target(stationIdUri(firstStationId)).request().get(StationInfo.class);
		Assert.assertNotNull(station);
		Assert.assertEquals("Peristeri", station.getStationName());
	}
	
	@Test
	public void testListAllStations() {
		
		List<StationInfo> stations = target(STATIONS).request().get(new GenericType<List<StationInfo>> () {
			
		});
		Assert.assertEquals(3, stations.size());
	}
	
	@Test
	public void testCreateNewStation() {
		
		List<Routes> routes = listRoutes();
		Assert.assertEquals(3, routes.size());
		Routes r = routes.get(0);
		
		StationInfo stationInfo = new  StationInfo("xaidari", 0, r.getId());
		
		Response response = target(STATIONS).request().post(Entity.entity(stationInfo, MediaType.APPLICATION_JSON));
		
		Assert.assertEquals(201, response.getStatus());
		List<Stations> foundStations = findStationsByName("xaidari");
		Assert.assertEquals(1, foundStations.size());
	}
	
	@Test
	public void testUpdateStation() {
		
		List<Stations> stations = findStationsByName("Kirki");
		Assert.assertEquals(1, stations.size());
		StationInfo stationInfo = StationInfo.wrap(stations.get(0));
		stationInfo.setStationName("Xalkidona");
		
		Response response = target(stationIdUri(Integer.toString(stationInfo.getId()))).request()
				.put(Entity.entity(stationInfo, MediaType.APPLICATION_XML));
		
		Assert.assertEquals(200, response.getStatus());
		List<Stations> foundStations = findStationsByName("Xalkidona");
		Assert.assertEquals(1,foundStations.size());
	}
	
	@Test
	public void testDeleteExistingStation () {
		
		List<Stations> stations = findStationsByName("Kirki");
		Assert.assertEquals(1, stations.size());
		Stations station = stations.get(0);
		
		Response response = target(stationIdUri(Integer.toString(station.getId()))).request().delete();
		
		Assert.assertEquals(200, response.getStatus());
		
		List<Stations> foundStations = findStationsByName("Kirki");
		Assert.assertEquals(0,foundStations.size());
	}
	
	@Test
	public void testDeleteNonExistingStation() {
		
		Response response = target(stationIdUri(Integer.toString(Integer.MAX_VALUE))).request().delete();
		
		Assert.assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
	}
	
	@Test
	public void testSearchStationByName() {
		System.out.println(MetroUri.stationSearchUri("Kirki"));
		List<StationInfo> stations = target(STATION_SEARCH).queryParam("stationname", "Kirki").request() 
				.get(new GenericType<List<StationInfo>>() {
					});
		Assert.assertEquals(1, stations.size());
	}

}









