package publisher;

import java.io.IOException;
import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import persistence.Initializer;
import persistence.JPAUtil;
	
public class Publisher {

	

		// Base URI the Grizzly HTTP server will listen on
		public static final String BASE_URI = "http://localhost:8080/metro/";

		/**
		 * Starts Grizzly HTTP server exposing JAX-RS resources defined in this
		 * application.
		 * 
		 * @return Grizzly HTTP server.
		 */
		public static HttpServer startServer() {

			// Create resource configuration that scans for JAX-RS
			// resources and providers in resource package
			final ResourceConfig rc = new ResourceConfig().packages("domain.resource");

			// Create and start a new instance of grizzly http server
			// exposing the Jersey application at BASE_URI
			HttpServer server = GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);

			return server;
		}

		/**
		 * Main method.
		 * 
		 * @param args
		 * @throws IOException
		 */
		@SuppressWarnings("deprecation")
		public static void main(String[] args) throws IOException {

			Initializer dataHelper = new Initializer();
			dataHelper.prepareData();

			final HttpServer server = startServer();
			System.out.println(String.format(
					"Jersey app started with WADL available at " + "%sapplication.wadl\nHit enter to stop it...",
					BASE_URI));
			System.in.read();
			server.stop();
			
			JPAUtil.getEntityManagerFactory().close();
			
		}

	

}
