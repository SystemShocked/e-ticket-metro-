package resource;

import static resource.MetroUri.ROUTES;


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

import domain.Routes;
import service.RouteServices;
import resource.RouteInfo;


@Path(ROUTES)
public class RouteResource extends AbstractResource{
	
	@Context
	UriInfo uriInfo;
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<RouteInfo> listAllRoutes() {
		EntityManager em = getEntityManager();
		RouteServices routeService = new RouteServices(em);
		List<Routes> routes = routeService.findAllRoutes();
		
		List<RouteInfo> routeInfo  = RouteInfo.wrap(routes);
		
		em.close();
		return routeInfo;
	}  
	
	@GET
	@Path("{id:[0-9]*}")
	@Produces(MediaType.APPLICATION_XML)
	public RouteInfo getRouteDetais(@PathParam("id") int id) {
		
		EntityManager em = getEntityManager();
		
		RouteServices routeService = new RouteServices(em);
		Routes route = routeService.findRoutesById(id);
		
		RouteInfo routeInfo = RouteInfo.wrap(route);
		em.close();
		
		return routeInfo;
	}
	
	@GET
	@Path("search")
	@Produces(MediaType.APPLICATION_XML)
	public List<RouteInfo> searchRouteByName(@QueryParam("routename") String routename) {
		
		EntityManager em = getEntityManager();
		RouteServices routeService = new RouteServices(em);
		List<Routes> routes = routeService.findRouteByName(routename);
		
		List<RouteInfo> routeInfo = RouteInfo.wrap(routes);
		
		em.close();
		return routeInfo;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response createRoute(RouteInfo routeInfo) {
		
		EntityManager em = getEntityManager();
		Routes route = routeInfo.getRoute(em);
		RouteServices routeService = new RouteServices(em);
		route = routeService.save(route);
		
		UriBuilder ub = uriInfo.getAbsolutePathBuilder();
		URI newRouteUri = ub.path(Integer.toString(route.getId())).build();
		
		em.close();
		return Response.created(newRouteUri).build();
		
	}
	
	@PUT
	@Path("{id:[0-9]*}")
	@Consumes(MediaType.APPLICATION_XML)
	public Response updateRoute(RouteInfo routeInfo) {
		
		EntityManager em = getEntityManager();
		
		Routes route = routeInfo.getRoute(em);
		
		RouteServices routeService = new RouteServices(em);
		route = routeService.save(route);
		
		em.close();
		return Response.ok().build();
	}
	
	@DELETE
	@Path("{Id:[0-9]*}")
	public Response deleteRoute(@PathParam("Id") int Id) {
		
		EntityManager em = getEntityManager();
		
		RouteServices routeService = new RouteServices(em);
		boolean result = routeService.deleteRoute(Id);
		
		if (!result) {
			em.close();
			return Response.status(Status.NOT_FOUND).build();
		}
		
		em.close();
		return Response.ok().build();
	}

}
















































