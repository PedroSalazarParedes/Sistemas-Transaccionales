package rest;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tm.FestivAndesMaster;
import vos.ListaLocalidades;
import vos.Lugar;

@Path("/lugares")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LugarServices {
	@Context
	private ServletContext context;

	private String getPath() {
		return context.getRealPath("WEB-INF/ConnectionData");
	}

	private String buildErrorMessage(Exception e) {
		return "{ \"ERROR\": \"" + e.getMessage() + "\"}";
	}
	
	@GET
	@Path("/consultar/{id: \\d+}/{criterio}")
	@Produces({ MediaType.TEXT_PLAIN })
	public Response consultarLugar(@PathParam("id") Integer id, @PathParam("criterio") String criterio) {
		FestivAndesMaster master = new FestivAndesMaster(getPath());
		String s;
		try {
			s = master.consultarLugares(id, criterio);
		} catch (Exception e) {
			return Response.status(500).entity(buildErrorMessage(e)).build();
		}
		return Response.status(200).entity(s).build();
	}
	
	
	@POST
	public Response addLugar(Lugar lugar, ListaLocalidades list) {
		FestivAndesMaster master = new FestivAndesMaster(getPath());
		try {
			master.addLugar(lugar, list);
		} catch (Exception e) {
			return Response.status(500).entity(buildErrorMessage(e)).build();
		}
		return Response.status(200).entity(lugar).build();
	}

}
