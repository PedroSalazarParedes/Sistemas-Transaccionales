package rest;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tm.FestivAndesMaster;
import vos.Funcion;
import vos.LocalidadesDetail;


@Path("/funciones")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FuncionServices {
	@Context
	private ServletContext context;

	private String getPath() {
		return context.getRealPath("WEB-INF/ConnectionData");
	}

	private String buildErrorMessage(Exception e) {
		return "{ \"ERROR\": \"" + e.getMessage() + "\"}";
	}
	
	@POST
	public Response addFuncion(Funcion f, LocalidadesDetail det) {
		FestivAndesMaster master = new FestivAndesMaster(getPath());
		try {
			master.addFuncion(f, det);
		} catch (Exception e) {
			return Response.status(500).entity(buildErrorMessage(e)).build();
		}
		return Response.status(200).entity(f).build();
	}


}
