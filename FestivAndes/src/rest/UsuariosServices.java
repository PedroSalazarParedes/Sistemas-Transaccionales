package rest;

import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tm.FestivAndesMaster;
import vos.Boleta;
import vos.Funcion;
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
	
	@POST
	@Path("/abonos")
	public Response registrarCompraAbono(Usuario usuario, List<Funcion> funciones) {
		FestivAndesMaster master = new FestivAndesMaster(getPath());
		try {
			master.registrarAbono(usuario,funciones);//TODO falta implementar el otro lado
		} catch (Exception e) {
			return Response.status(500).entity(buildErrorMessage(e)).build();
		}
		return Response.status(200).entity(usuario).build();
	}
	
	@PUT
	@Path("/devoluciones/boleta")
	public Response devolverBoleta(Usuario u, Boleta b) {
		FestivAndesMaster master = new FestivAndesMaster(getPath());
		try {
			master.devolverBoleta(u,b);//TODO falta implementar
		} catch (Exception e) {
			return Response.status(500).entity(buildErrorMessage(e)).build();
		}
		return Response.status(200).entity(b).build();
	}
	
	@PUT
	@Path("/devoluciones/abonos")
	public Response devolverAbono(Usuario u, List<Boleta>abono) {
		FestivAndesMaster master = new FestivAndesMaster(getPath());
		try {
			master.devolverAbono(u,abono);//TODO falta implementar
		} catch (Exception e) {
			return Response.status(500).entity(buildErrorMessage(e)).build();
		}
		return Response.status(200).entity(u).build();
	}
	
	
	
	

}
