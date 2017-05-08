package rest;

import java.sql.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
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

import tm.FestivAndesMaster;
import vos.Boleta;
import vos.Funcion;
import vos.Localidad;
import vos.Usuario;

@Path("/usuarios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuariosServices {

	@Context
	private ServletContext context;

	private String getPath() {
		return context.getRealPath("WEB-INF/ConnectionData");
	}

	private String buildErrorMessage(Exception e) {
		return "{ \"ERROR\": \"" + e.getMessage() + "\"}";
	}

	@POST
	public Response registrarUsuario(Usuario usuario) {
		FestivAndesMaster master = new FestivAndesMaster(getPath());
		try {
			master.addUsuario(usuario);
		} catch (Exception e) {
			return Response.status(500).entity(buildErrorMessage(e)).build();
		}
		return Response.status(200).entity(usuario).build();
	}
	
	@GET
	@Path("/RFC9")
	@Produces({MediaType.TEXT_PLAIN})
	public Response asistenciasPorCliente(@QueryParam("idCompania") int idCompania, @QueryParam("inicio") Date fechaInicio,@QueryParam("fin") Date fechaFin, @QueryParam("criterio") String criterio  ) {
		FestivAndesMaster master = new FestivAndesMaster(getPath());
		String s;
		try {
			s=master.consultarAsistencia(idCompania, fechaInicio, fechaFin, criterio);
		} catch (Exception e) {
			return Response.status(500).entity(buildErrorMessage(e)).build();
		}
		return Response.status(200).entity(s).build();
	}
	
	@GET
	@Path("/RFC10")
	@Produces({MediaType.TEXT_PLAIN})
	public Response asistenciasPorCliente2(@QueryParam("idCompania") int idCompania, @QueryParam("inicio") Date fechaInicio,@QueryParam("fin") Date fechaFin, @QueryParam("criterio") String criterio  ) {
		FestivAndesMaster master = new FestivAndesMaster(getPath());
		String s;
		try {
			s=master.consultarAsistencia1(idCompania, fechaInicio, fechaFin, criterio);
		} catch (Exception e) {
			return Response.status(500).entity(buildErrorMessage(e)).build();
		}
		return Response.status(200).entity(s).build();
	}
	
	@GET
	@Path("/RFC11")
	@Produces({MediaType.TEXT_PLAIN})
	public Response consultarCompra(@QueryParam("idUsuario") int idUsuario, @QueryParam("inicio") Date fechaInicio,@QueryParam("fin") Date fechaFin, @QueryParam("elementos") String elementos, @QueryParam("localidad") String localidad, @QueryParam("franjaHoraria") String franjaHoraria  ) {
		FestivAndesMaster master = new FestivAndesMaster(getPath());
		String s;
		try {
			s=master.consultarCompra(idUsuario, fechaInicio, fechaFin, elementos, localidad, franjaHoraria);
		} catch (Exception e) {
			return Response.status(500).entity(buildErrorMessage(e)).build();
		}
		return Response.status(200).entity(s).build();
	}
	
	@GET
	@Path("/RFC12")
	@Produces({MediaType.TEXT_PLAIN})
	public Response asistenciasPorCliente(@QueryParam("idUsuario") int idUsuario, @QueryParam("numero") int n) {
		FestivAndesMaster master = new FestivAndesMaster(getPath());
		String s;
		try {
			s=master.consultarBuenosClientes(idUsuario, n);
		} catch (Exception e) {
			return Response.status(500).entity(buildErrorMessage(e)).build();
		}
		return Response.status(200).entity(s).build();
	}

	
	
	
	
	

}
