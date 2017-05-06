package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
	 * M茅todo que registra como comprada la boleta que entra como par谩metro a la
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
				str += "Id funci贸n: " + rs.getString("ID_FUNCION")
						+ ", Nombre lugar " + rs.getString("NOMBRE_LUGAR")
						+ ", Usuario: No registrado, Valor producido: "
						+ rs.getString("PRODUCIDO")
						+ ", Numero de boletas vendidas: "
						+ rs.getString("BOLETAS_VENDIDAS")
						+ ", Porcentaje de ocupaci贸n: "
						+ rs.getString("porcentaje_ocupacion") + "\n";
			} else {
				str += "Id funci贸n: " + rs.getString("ID_FUNCION")
						+ ", Nombre lugar " + rs.getString("NOMBRE_LUGAR")
						+ ", Usuario: Cliente, Valor producido: "
						+ rs.getString("PRODUCIDO")
						+ ", Numero de boletas vendidas: "
						+ rs.getString("BOLETAS_VENDIDAS")
						+ ", Porcentaje de ocupaci贸n: "
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

			s += "Id funci贸n: " + rs.getString("ID_FUNCION") + ", Fecha "
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

			s += "Id funcion: " + rs.getString("ID_FUNCION") + ", Fecha "
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
	
	public String consultarAsistencia(int usuario) throws SQLException
	{
		//Consulta las funciones a las que asiste un cliente registrado de FestivAndes. 
		//Debe discriminar las funciones ya realizadas
		//o en curso, las funciones previstas
		
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String sql="";

		sql = "SELECT * FROM ISIS2304B241710.BOLETA NATURAL INNER JOIN ((ISIS2304B241710.FUNCION NATURAL INNER JOIN LUGAR) NATURAL INNER JOIN ESPECTACULO)";
		sql+=" WHERE id_usuario = "+usuario;
		
		String yaRealizadas ="";
		String enCurso ="";
		String previstas ="";
		
		PreparedStatement prepStmt = connection.prepareStatement(sql);
		resources.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		
		while(rs.next())
		{
			Date date = rs.getDate("FECHA");
			if(rs.getInt("REALIZADO")==1)
			{
				yaRealizadas+="-Id funcion: "+rs.getString("ID_FUNCION")+"\n"
				            +" Fecha: "+formatter.format(date)+"\n"
				            +" Espectaculo: "+rs.getString("NOMBRE_ESPECTACULO")+"\n"
				            +" Lugar: "+rs.getString("NOMBRE_LUGAR")+"\n";
			}
			else
			{
				Calendar c = Calendar.getInstance();
				Date hoy = c.getTime();
				if(hoy.before(date))
				{
					previstas+="-Id funcion: "+rs.getString("ID_FUNCION")+"\n"
		            +" Fecha: "+formatter.format(date)+"\n"
		            +" Espectaculo: "+rs.getString("NOMBRE_ESPECTACULO")+"\n"
		            +" Lugar: "+rs.getString("NOMBRE_LUGAR")+"\n";
				}
				else
				{
					enCurso+="-Id funcion: "+rs.getString("ID_FUNCION")+"\n"
		            +" Fecha: "+formatter.format(date)+"\n"
		            +" Espectaculo: "+rs.getString("NOMBRE_ESPECTACULO")+"\n"
		            +" Lugar: "+rs.getString("NOMBRE_LUGAR")+"\n";
				}
			}


		}

		String fina = "Realizadas: \n"+yaRealizadas+"\n"
				+"En curso: \n"+enCurso+"\n"
				+"Previstas: \n"+previstas;

		return fina;
		
	}
	
	public String consultarCompania(Integer idusuario) throws SQLException, Exception
	{
		String rta="";
		if(esCompania(idusuario))
		{
			String sql="";

			sql = "SELECT id_espectaculo, num_asistentes, SUM(valor) producido, (num_asistentes*100)/capacidad porcentaje"
					+ " FROM ISIS2304B241710.BOLETA NATURAL INNER JOIN (((ISIS2304B241710.FUNCION NATURAL INNER JOIN ESPECTACULO) NATURAL INNER JOIN COMPANIA_TEATRO) NATURAL INNER JOIN LUGAR)";
			sql+=" WHERE id_compania = "+idusuario;
			sql+=" AND realizado = 1";
			sql+=" GROUP BY id_espectaculo, num_asistentes, id_funcion, capacidad";
			sql+=" ORDER BY id_espectaculo";
			
			PreparedStatement prepStmt = connection.prepareStatement(sql);
			resources.add(prepStmt);
			ResultSet rs = prepStmt.executeQuery();
			Long idesp=null;
			String temp="";
			double valor=0;

			while(rs.next())
			{
				Long id = Long.parseLong(rs.getString("id_espectaculo"));
				if(idesp==null )
				{
					idesp=id;
					rta+="-Id espectaculo: "+rs.getString("id_espectaculo")+"\n"
							+" Asistencia total: "+rs.getString("num_asistentes")+"\n"
							+" Asistencia clientes registrados: "+darClientesRegistrados(id)+"\n"
							+" Dinero generado en taquilla: ";
					temp="";
				}
				else if(!idesp.equals(id))
				{
					idesp=id;
					rta+=valor+"\n";
					rta+=temp;
					rta+="-Id espectaculo: "+rs.getString("id_espectaculo")+"\n"
							+" Asistencia total: "+rs.getString("num_asistentes")+"\n"
							+" Asistencia clientes registrados: "+darClientesRegistrados(id)+"\n"
							+" Dinero generado en taquilla: ";
					temp="";
					valor=0;
				}

				temp+=" *Id funcion: "+rs.getString("ID_FUNCION")+"\n"
						+"  Porcentaje de ocupaci髇: " +rs.getString("porcentaje")+" % \n";

				valor+=rs.getDouble("producido");
			}
		}
		else if(esAdministrador(idusuario))
		{
			String sql="";

			sql = "SELECT id_compania, nombre_compania, id_espectaculo, num_asistentes, SUM(valor) producido, (num_asistentes*100)/capacidad porcentaje"
					+ " FROM ISIS2304B241710.BOLETA NATURAL INNER JOIN (((ISIS2304B241710.FUNCION NATURAL INNER JOIN ESPECTACULO) NATURAL INNER JOIN COMPANIA_TEATRO) NATURAL INNER JOIN LUGAR)";
			sql+=" AND realizado = 1";
			sql+=" GROUP BY id_compania, nombre_compania, id_espectaculo, num_asistentes, id_funcion, capacidad";
			sql+=" ORDER BY id_compania, id_espectaculo";
			
			PreparedStatement prepStmt = connection.prepareStatement(sql);
			resources.add(prepStmt);
			ResultSet rs = prepStmt.executeQuery();
			Long idesp=null;
			String idcomp = null;
			String temp="";
			double valor=0;

			while(rs.next())
			{
				Long id = Long.parseLong(rs.getString("id_espectaculo"));
				String com = rs.getString("id_compania");
				String nombre = rs.getString("nombre_compania");
				
				if(idcomp==null)
				{
					idcomp=com;
					rta+="NOMBRE DE LA COMPANIA: "+nombre+" - ID: "+com+"\n";
				}
				if(idesp==null)
				{
					idesp=id;
					rta+="-Id espectaculo: "+rs.getString("id_espectaculo")+"\n"
							+" Asistencia total: "+rs.getString("num_asistentes")+"\n"
							+" Asistencia clientes registrados: "+darClientesRegistrados(id)+"\n"
							+" Dinero generado en taquilla: ";
					temp="";
				}
				else if(!idesp.equals(id) && idcomp.equals(com))
				{
					idesp=id;
					rta+=valor+"\n";
					rta+=temp;
					rta+="-Id espectaculo: "+rs.getString("id_espectaculo")+"\n"
							+" Asistencia total: "+rs.getString("num_asistentes")+"\n"
							+" Asistencia clientes registrados: "+darClientesRegistrados(id)+"\n"
							+" Dinero generado en taquilla: ";
					temp="";
					valor=0;
				}
				else if(!idesp.equals(id) && !idcomp.equals(com))
				{
					idesp=id;
					rta+=valor+"\n";
					rta+=temp;
					rta+="\n";
					rta+="NOMBRE DE LA COMPANIA: "+nombre+" - ID: "+com+"\n";
					rta+="-Id espectaculo: "+rs.getString("id_espectaculo")+"\n"
							+" Asistencia total: "+rs.getString("num_asistentes")+"\n"
							+" Asistencia clientes registrados: "+darClientesRegistrados(id)+"\n"
							+" Dinero generado en taquilla: ";
					idcomp=com;
					temp="";
					valor=0;
				}

				temp+=" *Id funcion: "+rs.getString("ID_FUNCION")+"\n"
						+"  Porcentaje de ocupaci髇: " +rs.getString("porcentaje")+" % \n";

				valor+=rs.getDouble("producido");
			}
		}
		else
		{
			throw new Exception("El usuario no tiene autorizaci髇 para realizar esta consulta");
		}
		
		
		
		return rta;
	}
	
	public int darClientesRegistrados(Long idespectaculo) throws SQLException
	{
		String sql="";

		sql = "SELECT count(*) hola"
				+ " FROM ISIS2304B241710.BOLETA NATURAL INNER JOIN (ISIS2304B241710.FUNCION NATURAL INNER JOIN ESPECTACULO)";
		sql+=" WHERE id_espectaculo = "+idespectaculo;
		sql+=" AND id_usuario IS NOT NULL";
		
		PreparedStatement prepStmt = connection.prepareStatement(sql);
		resources.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		rs.next();
		
		return rs.getInt("hola");
	}
	
	public boolean esCliente(Integer idusuario) throws SQLException
	{
		String sql="";

		sql = "SELECT * FROM ISIS2304B241710.USUARIO";
		sql+=" WHERE id_usuario = "+idusuario;


		PreparedStatement prepStmt = connection.prepareStatement(sql);
		resources.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		rs.next();
		
		return rs.getString("ROL").equals("Cliente");
	}
	
	public boolean esAdministrador(Integer idusuario) throws SQLException
	{
		String sql="";

		sql = "SELECT * FROM ISIS2304B241710.USUARIO";
		sql+=" WHERE id_usuario = "+idusuario;


		PreparedStatement prepStmt = connection.prepareStatement(sql);
		resources.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		rs.next();
		
		return rs.getString("ROL").equals("Administrador");
	}
	
	public boolean esCompania(Integer idusuario) throws SQLException
	{
		String sql="";

		sql = "SELECT * FROM ISIS2304B241710.USUARIO";
		sql+=" WHERE id_usuario = "+idusuario;


		PreparedStatement prepStmt = connection.prepareStatement(sql);
		resources.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		rs.next();
		
		return rs.getString("ROL").equals("Compania");
	}
}