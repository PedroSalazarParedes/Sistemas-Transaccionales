package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Espectaculo;



public class ConsultasDAO {

	private ArrayList<Object> resources;
	private Connection connection;

	public ConsultasDAO() {
		resources = new ArrayList<Object>();
	}
	
	public void setConnection(Connection c) {
		connection = c;
	}

	public void closeResources() {
		for (Object ob : resources) {
			if (ob instanceof PreparedStatement)
				try {
					((PreparedStatement) ob).close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
		}
	}
	
	/**
	 * Método que registra como comprada la boleta que entra como parámetro a la base de datos.
	 * @param boleta- el boleta a agregar. video !=  null
	 * <b> post: </b> se ha agregado el espectaculo a la base de datos en la transaction actual. pendiente que el festival master
	 * haga commit para que el boleta baje  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el video a la base de datos
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void generarReporteFuncion(Long idfuncion) throws SQLException, Exception {

		String sql = "SELECT nombre_localidad, cliente, SUM(valor) producido, COUNT(*) boletas_vendidas "
				+"FROM (BOLETA NATURAL INNER JOIN LOCALIDAD) "
				+"WHERE id_funcion = "+idfuncion
				+" GROUP BY id_localidad, nombre_localidad, cliente"
				+" ORDER BY nombre_localidad";

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = connection.prepareStatement(sql);
		resources.add(prepStmt);
		prepStmt.executeQuery();

	}


	public void generarReporteEspectaculo(Long idespectaculo) throws SQLException, Exception 
	{
		String sql = "SELECT id_funcion, nombre_lugar, sum(valor) producido, count(*) boletas_vendidas, count(*)/capacidad porcentaje_ocupacion"
				+" WHERE id_espectaculo = "+idespectaculo 
				+" GROUP BY id_funcion, nombre_lugar, capacidad, cliente"
				+" ORDER BY nombre_lugar, id_funcion";
		
		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = connection.prepareStatement(sql);
		resources.add(prepStmt);
		prepStmt.executeQuery();


	}	
	
	
	
	public void consultarRentabilidadCompanias()
	{
		
	
	}
	
	
	
	public ArrayList<Espectaculo> getEspectaculosPopulares(String fechaInic, String fechaFin) throws SQLException, Exception{
		ArrayList<Espectaculo> e = new ArrayList<>();
		String sql = "SELECT E.id_espectaculo, nombre_espectaculo duracion, costo_realizacion, participacion_publico, descripcion, idioma, tipo_traduccion, req_tecnicos, id_categoria"
				+" FROM ESPECTACULO E, FUNCION F"
				+" WHERE E.id_espectaculo=F.id_espectaculo"
				+" AND realizado=1"
				+" AND HORA < '"+fechaFin+"' AND HORA > '"+fechaInic+"'"
				+" GROUP BY E.id_espectaculo, nombre_espectaculo, duracion, costo_realizacion, participacion_publico, descripcion, idioma, tipo_traduccion, req_tecnicos, id_categoria"
				+" HAVING SUM(num_asistentes)= (SELECT MAX(total_asistentes)"
						                        +" FROM (SELECT E.id_espectaculo, SUM(num_asistentes) total_asistentes"
                                                         +" FROM ESPECTACULO E, FUNCION F"
                                                         +" WHERE E.id_espectaculo=F.id_espectaculo"
                                                         +" AND realizado=1"
                                                         +" AND HORA < '"+fechaFin+"' AND HORA > '"+fechaInic+"'"
                                                         +"GROUP BY E.id_espectaculo))";
                     
	


		PreparedStatement prepStmt = connection.prepareStatement(sql);
		resources.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		

		while (rs.next()) 
		{
			Long id = Long.parseLong(rs.getString("ID_ESPECTACULO"));
			String name = rs.getString("NOMBRE_ESPECTACULO");
			int duracion = Integer.parseInt(rs.getString("DURACION"));
			double costo = Double.parseDouble(rs.getString("COSTO_REALIZACION"));
			int participacion = Integer.parseInt(rs.getString("PARTICIPACION_PUBLICO"));
			String descripcion = rs.getString("DESCRIPCION");
			String publi = rs.getString("PUBLICO_OBJETIVO");
			String idio = rs.getString("IDIOMA");
			String tipo = rs.getString("TIPO_TRADUCCION");
			String reqs = rs.getString("REQ_TECNICOS");
			Long idCat = Long.parseLong(rs.getString("ID_CATEGORIA"));
			e.add(new Espectaculo(id, name, duracion, costo, participacion, descripcion, publi, idio, tipo, reqs, idCat));
		}
		
		return e;
		
	}
}