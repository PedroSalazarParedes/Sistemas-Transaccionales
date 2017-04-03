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
	 * Método que registra como comprada la boleta que entra como parámetro a la
	 * base de datos.
	 * 
	 * @param boleta
	 *            - el boleta a agregar. video != null <b> post: </b> se ha
	 *            agregado el espectaculo a la base de datos en la transaction
	 *            actual. pendiente que el festival master haga commit para que
	 *            el boleta baje a la base de datos.
	 * @throws SQLException
	 *             - Cualquier error que la base de datos arroje. No pudo
	 *             agregar el video a la base de datos
	 * @throws Exception
	 *             - Cualquier error que no corresponda a la base de datos
	 */
	public String generarReporteFuncion(Integer id) throws SQLException,
			Exception {
		String str = "";
		String sql = "SELECT nombre_localidad, cliente, SUM(valor) producido, COUNT(*) boletas_vendidas "
				+ "FROM (ISIS2304B241710.BOLETA NATURAL INNER JOIN ISIS2304B241710.LOCALIDAD) "
				+ "WHERE id_funcion = "
				+ id
				+ " GROUP BY id_localidad, nombre_localidad, cliente"
				+ " ORDER BY nombre_localidad";

		// System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = connection.prepareStatement(sql);
		resources.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			if (rs.getString("CLIENTE") == "0") {
				str += "Nombre localidad: " + rs.getString("NOMBRE_LOCALIDAD")
						+ ", Usuario: No registrado, Valor producido: "
						+ rs.getString("PRODUCIDO")
						+ ", Numero de boletas vendidas: "
						+ rs.getString("BOLETAS_VENDIDAS") + "\n";
			} else {
				str += "Nombre localidad: " + rs.getString("NOMBRE_LOCALIDAD")
						+ ", Usuario: Cliente, Valor producido: "
						+ rs.getString("PRODUCIDO")
						+ ", Numero de boletas vendidas: "
						+ rs.getString("BOLETAS_VENDIDAS") + "\n";
			}
		}
		return str;

	}

	public String generarReporteEspectaculo(Integer id) throws SQLException,
			Exception {
		String str = "";
		String sql = "SELECT id_funcion, nombre_lugar, cliente, sum(valor) producido, count(*) boletas_vendidas, count(*)/capacidad porcentaje_ocupacion"
				+ " FROM (ISIS2304B241710.FUNCION NATURAL INNER JOIN (ISIS2304B241710.BOLETA NATURAL INNER JOIN ISIS2304B241710.LOCALIDAD)) NATURAL INNER JOIN ISIS2304B241710.LUGAR"
				+ " WHERE id_espectaculo = "
				+ id
				+ " GROUP BY id_funcion, nombre_lugar, capacidad, cliente"
				+ " ORDER BY nombre_lugar, id_funcion";

		// System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = connection.prepareStatement(sql);
		resources.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			if (rs.getString("CLIENTE") == "0") {
				str += "Id función: " + rs.getString("ID_FUNCION")
						+ ", Nombre lugar " + rs.getString("NOMBRE_LUGAR")
						+ ", Usuario: No registrado, Valor producido: "
						+ rs.getString("PRODUCIDO")
						+ ", Numero de boletas vendidas: "
						+ rs.getString("BOLETAS_VENDIDAS")
						+ ", Porcentaje de ocupación: "
						+ rs.getString("porcentaje_ocupacion") + "\n";
			} else {
				str += "Id función: " + rs.getString("ID_FUNCION")
						+ ", Nombre lugar " + rs.getString("NOMBRE_LUGAR")
						+ ", Usuario: Cliente, Valor producido: "
						+ rs.getString("PRODUCIDO")
						+ ", Numero de boletas vendidas: "
						+ rs.getString("BOLETAS_VENDIDAS")
						+ ", Porcentaje de ocupación: "
						+ rs.getString("porcentaje_ocupacion") + "\n";
			}
		}
		return str;

	}

	public String consultarRentabilidadCompanias(String compania) {
		String s = "";
		if (compania.equals("All")) {

		} else {

		}
		return s;
	}

	public ArrayList<Espectaculo> getEspectaculosPopulares(String fechaInic,
			String fechaFin) throws SQLException, Exception {
		ArrayList<Espectaculo> e = new ArrayList<>();
		String sql = "SELECT E.id_espectaculo, nombre_espectaculo duracion, costo_realizacion, participacion_publico, descripcion, idioma, tipo_traduccion, req_tecnicos, id_categoria"
				+ " FROM ISIS2304B241710.ESPECTACULO E, ISIS2304B241710.FUNCION F"
				+ " WHERE E.id_espectaculo=F.id_espectaculo"
				+ " AND realizado = 1"
				+ " AND HORA < '"
				+ fechaFin
				+ "' AND HORA > '"
				+ fechaInic
				+ "'"
				+ " GROUP BY E.id_espectaculo, nombre_espectaculo, duracion, costo_realizacion, participacion_publico, descripcion, idioma, tipo_traduccion, req_tecnicos, id_categoria"
				+ " HAVING SUM(num_asistentes)= (SELECT MAX(total_asistentes)"
				+ " FROM (SELECT E.id_espectaculo, SUM(num_asistentes) total_asistentes"
				+ " FROM ISIS2304B241710.ESPECTACULO E, ISIS2304B241710.FUNCION F"
				+ " WHERE E.id_espectaculo=F.id_espectaculo"
				+ " AND realizado=1"
				+ " AND HORA < '"
				+ fechaFin
				+ "' AND HORA > '"
				+ fechaInic
				+ "'"
				+ "GROUP BY E.id_espectaculo))";

		PreparedStatement prepStmt = connection.prepareStatement(sql);
		resources.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			Long id = Long.parseLong(rs.getString("ID_ESPECTACULO"));
			String name = rs.getString("NOMBRE_ESPECTACULO");
			int duracion = Integer.parseInt(rs.getString("DURACION"));
			double costo = Double
					.parseDouble(rs.getString("COSTO_REALIZACION"));
			int participacion = Integer.parseInt(rs
					.getString("PARTICIPACION_PUBLICO"));
			String descripcion = rs.getString("DESCRIPCION");
			String publi = rs.getString("PUBLICO_OBJETIVO");
			String idio = rs.getString("IDIOMA");
			String tipo = rs.getString("TIPO_TRADUCCION");
			String reqs = rs.getString("REQ_TECNICOS");
			Long idCat = Long.parseLong(rs.getString("ID_CATEGORIA"));
			e.add(new Espectaculo(id, name, duracion, costo, participacion,
					descripcion, publi, idio, tipo, reqs, idCat));
		}

		return e;

	}

	public String consultarFunciones(String criterio) throws SQLException,
			Exception {
		String s = "";
		String sql = "SELECT id_funcion, hora, realizado, num_asistentes, id_lugar, id_espectaculo"
				+ " FROM ((ISIS2304B241710.ESPECTACULO NATURAL INNER JOIN ISIS2304B241710.FUNCION) NATURAL INNER JOIN (ISIS2304B241710.COMPANIA_TEATRO NATURAL INNER JOIN ISIS2304B241710.REALIZADO_POR)) NATURAL INNER JOIN (ISIS2304B241710.CATEGORIA NATURAL INNER JOIN ISIS2304B241710.CATEGORIA_ESPECTACULO)"
				+ " ORDER BY " + criterio;

		// System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = connection.prepareStatement(sql);
		resources.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {

			s += "Id función: " + rs.getString("ID_FUNCION") + ", Fecha "
					+ rs.getString("HORA") + ", Realizado: "
					+ rs.getString("REALIZADO") + ", Numero de asistentes: "
					+ rs.getString("NUM_ASISTENTES") + ", Id lugar: "
					+ rs.getString("id_lugar") + "\n";

		}
		return s;
	}

	public String consultarLugares(Integer id, String criterio)
			throws SQLException, Exception {
		String s = "";
		String sql = "SELECT id_funcion, hora, realizado, num_asistentes, id_lugar, id_espectaculo, nombre_localidad, valor, count(*) boleteria"
				+ " FROM ((ISIS2304B241710.BOLETA NATURAL INNER JOIN ISIS2304B241710.FUNCION) NATURAL INNER JOIN ISIS2304B241710.ESPECTACULO) NATURAL INNER JOIN ISIS2304B241710.LUGAR"
				+ " WHERE ID_LUGAR = "
				+ id
				+ " AND disponible = 1"
				+ " GROUP BY id_funcion, hora, realizado, num_asistentes, id_lugar, id_espectaculo, nombre_localidad, valor"
				+ " ORDER BY " + criterio;

		// System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = connection.prepareStatement(sql);
		resources.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {

			s += "Id función: " + rs.getString("ID_FUNCION") + ", Fecha "
					+ rs.getString("HORA") + ", Realizado: "
					+ rs.getString("REALIZADO") + ", Numero de asistentes: "
					+ rs.getString("NUM_ASISTENTES") + ", Id lugar: "
					+ rs.getString("id_lugar") + ", Nombre localidad: "
					+ rs.getString("NOMBRE_LOCALIDAD") + ", Valor: "
					+ rs.getString("VALOR") + ", Boleteria disponible: "
					+ rs.getString("BOLETERIA") + "\n";

		}
		return s;
	}
}