package resource;

import static resource.MetroUri.STATIONS;

import java.net.URI;
import java.util.List;

import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import domain.Stations;
import service.StationServices;
import soap.StationInfo;


@Path(STATIONS)
public class StationResource extends AbstractResource{
	
	@Context
	UriInfo uriInfo;
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<StationInfo> listAllStations() {
		EntityManager em = getEntityManager();
		StationServices stationService = new StationServices(em);
		List<Stations> stations = stationService.findAllStations();
		
		List<StationInfo> stationInfo = StationInfo.wrap(stations);
		
		em.close();
		return stationInfo;
	}
	
	@GET
	@Path("{id:[0-9]*}")
	@Produces(MediaType.APPLICATION_XML)
	public StationInfo getStationDetails(@PathParam("id") int id) {
		
		EntityManager em = getEntityManager();
		
		StationServices stationService = new StationServices(em);
		Stations station = stationService.findStationsById(id);
		
		StationInfo stationInfo = StationInfo.wrap(station);
		em.close();
		
		return stationInfo;
	}
	
	@GET
	@Path("search")
	@Produces(MediaType.APPLICATION_XML)
	public List<StationInfo> searchStationByName(@QueryParam("stationname") String stationname) {
		
		EntityManager em = getEntityManager();
		StationServices stationService = new StationServices(em);
		List<Stations> stations = stationService.findStationByName(stationname);
		
		List<StationInfo> stationInfo = StationInfo.wrap(stations);
		
		em.close();
		return stationInfo;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response createStation(StationInfo stationInfo) {
		
		EntityManager em = getEntityManager();
		
		Stations station = stationInfo.getStation(em);
		
		StationServices stationService = new StationServices(em);
		station = stationService.save(station);
		
		UriBuilder ub = uriInfo.getAbsolutePathBuilder();
		URI newStationUri = ub.path(Integer.toString(station.getId())).build();
		
		em.close();
		return Response.created(newStationUri).build();
		
	}
	
	@PUT
	@Path("{id:[0-9]*}")
	@Consumes(MediaType.APPLICATION_XML)
	public Response updateStation(StationInfo stationInfo) {
		
		EntityManager em = getEntityManager();
		
		Stations station = stationInfo.getStation(em);
		
		StationServices stationService = new StationServices(em);
		station = stationService.save(station);
		
		em.close();
		return Response.ok().build();
	}
	
	@DELETE
	@Path("{Id:[0-9]*}")
	public Response deleteStation(@PathParam("Id") int Id) {
		
		EntityManager em = getEntityManager();
		
		StationServices stationService = new StationServices(em);
		boolean result = stationService.deleteStation(Id);
		
		if (!result) {
			em.close();
			return Response.status(Status.NOT_FOUND).build();
		}
		
		em.close();
		return Response.ok().build();
	}

}

























































