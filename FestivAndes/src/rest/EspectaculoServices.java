package rest;

import java.util.Date;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tm.FestivAndesMaster;
import vos.Espectaculo;
import vos.ListaCompanias;
import vos.ListaEspectaculos;

@Path("/espectaculos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EspectaculoServices {

	@Context
	private ServletContext context;

	private String getPath() {
		return context.getRealPath("WEB-INF/ConnectionData");
	}

	private String buildErrorMessage(Exception e) {
		return "{ \"ERROR\": \"" + e.getMessage() + "\"}";
	}

	// GETS
	@GET
	@Path("/populares")
	public Response espectaculosPopulares(@QueryParam("from") Date from, @QueryParam("to") Date to) {
		FestivAndesMaster master = new FestivAndesMaster(getPath());
		ListaEspectaculos esps;
		try {
			String fromm = from.toString(); String too = to.toString();
			esps = master.darEspectaculosMasPopulares(fromm,too);
		} catch (Exception e) {
			return Response.status(500).entity(buildErrorMessage(e)).build();
		}
		return Response.status(200).entity(esps).build();
	}
	
	@GET
	@Path("/reporte/{id: \\d+}")
	public Response reporteEspectaculo(@PathParam("id") Integer id) {
		FestivAndesMaster master = new FestivAndesMaster(getPath());
		String s;
		try {
			s = master.darReporteDeEspectaculo(id);
		} catch (Exception e) {
			return Response.status(500).entity(buildErrorMessage(e)).build();
		}
		return Response.status(200).entity(s).build();
	}
	

	// POSTS
	@POST
	public Response addEspectaculo(Espectaculo esp, ListaCompanias list) {
		FestivAndesMaster master = new FestivAndesMaster(getPath());
		try {
			master.addEspectaculo(esp, list);
		} catch (Exception e) {
			return Response.status(500).entity(buildErrorMessage(e)).build();
		}
		return Response.status(200).entity(esp).build();
	}

}
