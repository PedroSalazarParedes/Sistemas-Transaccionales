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
import vos.BoletaDevolver;
import vos.BoletaMultiple;
import vos.Devolucion;
import vos.Funcion1;
import vos.ListaDevolucion;
import vos.Localidad;

@Path("/boletas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BoletaServices {
	
	@Context
	private ServletContext context;

	private String getPath() {
		return context.getRealPath("WEB-INF/ConnectionData");
	}

	private String buildErrorMessage(Exception e) {
		return "{ \"ERROR\": \"" + e.getMessage() + "\"}";
	}
	
	@POST
	public Response venderBoleta(Boleta b) {
		FestivAndesMaster master = new FestivAndesMaster(getPath());
		try {
			master.registrarBoletaComprada(b, 0);
		} catch (Exception e) {
			return Response.status(500).entity(buildErrorMessage(e)).build();
		}
		return Response.status(200).entity(b).build();
	}
	
	@POST
	@Path("/{idUsuario: \\d+}")
	public Response venderBoleta(Boleta b,@PathParam("idUsuario") int id) {
		FestivAndesMaster master = new FestivAndesMaster(getPath());
		try {
			master.registrarBoletaComprada(b, id);
		} catch (Exception e) {
			return Response.status(500).entity(buildErrorMessage(e)).build();
		}
		return Response.status(200).entity(b).build();
	}
	
	@POST
	@Path("/multiples/{idUsuario: \\d+}")
	public Response venderMultiplesBoletas(BoletaMultiple boleta, @PathParam("idUsuario") int id) {
		FestivAndesMaster master = new FestivAndesMaster(getPath());
		try {
			master.registrarBoletasCompradas(boleta, id);
		} catch (Exception e) {
			return Response.status(500).entity(buildErrorMessage(e)).build();
		}
		return Response.status(200).entity(boleta).build();
	}
	
	
	@POST
	@Path("/abonos")
	public Response registrarCompraAbono(List<Funcion1> funciones, List<Localidad> localidades, @PathParam("idUsuario") int id) {
		FestivAndesMaster master = new FestivAndesMaster(getPath());
		try {
			master.registrarAbono(id,funciones, localidades);//TODO falta implementar el otro lado
		} catch (Exception e) {
			return Response.status(500).entity(buildErrorMessage(e)).build();
		}
		return Response.status(200).entity(funciones).build();
	}
	
	@PUT
	@Path("/devoluciones/boleta")
	public Response devolverBoleta(BoletaDevolver b, @PathParam("idUsuario") int id) {
		FestivAndesMaster master = new FestivAndesMaster(getPath());
		Devolucion x;
		try {
			x = master.devolverBoleta(b,id);//TODO falta implementar
		} catch (Exception e) {
			return Response.status(500).entity(buildErrorMessage(e)).build();
		}
		return Response.status(200).entity(x).build();
	}
	
	@PUT
	@Path("/devoluciones/abonos")
	public Response devolverAbono(List<BoletaDevolver>abono, @PathParam("idUsuario") int id) {
		FestivAndesMaster master = new FestivAndesMaster(getPath());
		ListaDevolucion x;
		try {
			x = master.devolverAbono(abono,id);//TODO falta implementar
		} catch (Exception e) {
			return Response.status(500).entity(buildErrorMessage(e)).build();
		}
		return Response.status(200).entity(x).build();
	}
	
	
	
}
