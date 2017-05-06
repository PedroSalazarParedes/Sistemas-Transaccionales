package rest;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
	@POST
	@Path("/realizada")
	public Response registrarRealizacion(Funcion f) {
		FestivAndesMaster master = new FestivAndesMaster(getPath());
		try {
			master.registrarFuncionRealizada(f.getId());
		} catch (Exception e) {
			return Response.status(500).entity(buildErrorMessage(e)).build();
		}
		return Response.status(200).entity(f).build();
	}
	
	@GET
	@Produces({ MediaType.TEXT_PLAIN })
	public Response test() {
		return Response.status(200).entity("hola!").build();
	}
	
	@GET
	@Path("/reporte/{id: \\d+}")
	@Produces({ MediaType.TEXT_PLAIN })
	public Response reporteFuncion(@PathParam("id") Integer id) {
		FestivAndesMaster master = new FestivAndesMaster(getPath());
		String s;
		try {
			s = master.darReporteDeFuncion(id);
		} catch (Exception e) {
			return Response.status(500).entity(buildErrorMessage(e)).build();
		}
		return Response.status(200).entity(s).build();
	}

	@PUT
	@Path("/cancelarFuncion/{id: \\d+}")
	public Response cancelar(@PathParam("id") Long id, @PathParam("cliente") Integer c) {
		FestivAndesMaster master = new FestivAndesMaster(getPath());
		try {
			master.cancelarFuncion(id,c);//TODO 
		} catch (Exception e) {
			return Response.status(500).entity(buildErrorMessage(e)).build();
		}
		return Response.status(200).entity(id).build();
	}
	
	@GET
	@Path("/asistencias/{idUsuario: \\d+}")
	@Produces({MediaType.TEXT_PLAIN})
	public Response asistenciasPorCliente(@PathParam("idUsuario") Integer id,@PathParam("cliente") Integer cl) {
		FestivAndesMaster master = new FestivAndesMaster(getPath());
		String s;
		try {
			s=master.asistenciasPorCliente(id,cl);//TODO 
		} catch (Exception e) {
			return Response.status(500).entity(buildErrorMessage(e)).build();
		}
		return Response.status(200).entity(s).build();
	}

}
