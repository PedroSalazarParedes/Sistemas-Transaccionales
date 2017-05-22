package rest;

import java.sql.Date;

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
	
	@GET
	@Path("agregar")
	public Response sayHello() {
		FestivAndesMaster master = new FestivAndesMaster(getPath());
		try {
			master.agregarUsuarios();
		} catch (Exception e) {
			return Response.status(500).entity(buildErrorMessage(e)).build();
		}
		return Response.status(200).entity("Todo bien").build();
	}
	
	@GET
	@Path("/agregar/{name}")
	public Response getVideoName(@javax.ws.rs.PathParam("name") int name) {
		FestivAndesMaster master = new FestivAndesMaster(getPath());
		try {
			master.agregarUsuarios(name);
		} catch (Exception e) {
			return Response.status(500).entity(buildErrorMessage(e)).build();
		}
		return Response.status(200).entity("Todo bien").build();
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
	@Path("/usuario/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getUsuario(@javax.ws.rs.PathParam("id") Integer id) {
		FestivAndesMaster tm = new FestivAndesMaster(getPath());
		Usuario u;
		try {
			
			u = tm.darUsuario(id);
		} catch (Exception e) {
			return Response.status(500).entity(buildErrorMessage(e)).build();
		}
		return Response.status(200).entity(u).build();
	}
	@GET
	@Path("/RFC9")
	@Produces({MediaType.TEXT_PLAIN})
	public Response asistenciasPorCliente(@QueryParam("idCompania") int idCompania, @QueryParam("inicio") String fechaInicio,@QueryParam("fin") String fechaFin, @QueryParam("criterio") String criterio  ) {
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
	public Response asistenciasPorCliente2(@QueryParam("idCompania") int idCompania, @QueryParam("inicio") String fechaInicio,@QueryParam("fin") String fechaFin, @QueryParam("criterio") String criterio  ) {
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
	public Response consultarCompra(@QueryParam("idUsuario") int idUsuario, @QueryParam("inicio") String fechaInicio,@QueryParam("fin") String fechaFin, @QueryParam("elementos") String elementos, @QueryParam("localidad") String localidad, @QueryParam("franjaHoraria") String franjaHoraria, @QueryParam("dia") String dia ) {
		FestivAndesMaster master = new FestivAndesMaster(getPath());
		String s;
		try {
			s=master.consultarCompra(idUsuario, fechaInicio, fechaFin, elementos, localidad, franjaHoraria, dia);
		} catch (Exception e) {
			return Response.status(500).entity(buildErrorMessage(e)).build();
		}
		return Response.status(200).entity(s).build();
	}
	
	@GET
	@Path("/RFC12")
	@Produces({MediaType.TEXT_PLAIN})
	public Response buenosClientes(@QueryParam("idUsuario") int idUsuario, @QueryParam("numero") int n) {
		FestivAndesMaster master = new FestivAndesMaster(getPath());
		String s;
		try {
			s=master.consultarBuenosClientes(idUsuario, n);
		} catch (Exception e) {
			return Response.status(500).entity(buildErrorMessage(e)).build();
		}
		return Response.status(200).entity(s).build();
	}
	
	@GET
	@Path("/prueba")
	@Produces({MediaType.TEXT_PLAIN})
	public Response buenosClientes() {
		FestivAndesMaster master = new FestivAndesMaster(getPath());
		String s;
		try {
			s=master.consultarBuenosClientes();
		} catch (Exception e) {
			return Response.status(500).entity(buildErrorMessage(e)).build();
		}
		return Response.status(200).entity(s).build();
	}

	
	
	
	
	

}
