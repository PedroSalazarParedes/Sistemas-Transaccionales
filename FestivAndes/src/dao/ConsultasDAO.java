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
	 * MÃ©todo que registra como comprada la boleta que entra como parÃ¡metro a la
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
				str += "Id funcion: " + rs.getString("ID_FUNCION")
						+ ", Nombre lugar " + rs.getString("NOMBRE_LUGAR")
						+ ", Usuario: No registrado, Valor producido: "
						+ rs.getString("PRODUCIDO")
						+ ", Numero de boletas vendidas: "
						+ rs.getString("BOLETAS_VENDIDAS")
						+ ", Porcentaje de ocupacion: "
						+ rs.getString("porcentaje_ocupacion") + "\n";
			} else {
				str += "Id funcion: " + rs.getString("ID_FUNCION")
						+ ", Nombre lugar " + rs.getString("NOMBRE_LUGAR")
						+ ", Usuario: Cliente, Valor producido: "
						+ rs.getString("PRODUCIDO")
						+ ", Numero de boletas vendidas: "
						+ rs.getString("BOLETAS_VENDIDAS")
						+ ", Porcentaje de ocupacion: "
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

			s += "Id funciOn: " + rs.getString("ID_FUNCION") + ", Fecha "
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
						+"  Porcentaje de ocupación: " +rs.getString("porcentaje")+" % \n";

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
						+"  Porcentaje de ocupación: " +rs.getString("porcentaje")+" % \n";

				valor+=rs.getDouble("producido");
			}
		}
		else
		{
			throw new Exception("El usuario no tiene autorización para realizar esta consulta");
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
	
	
	public boolean esGerenteGeneral(Integer idusuario) throws SQLException
	{
		String sql="";

		sql = "SELECT * FROM ISIS2304B241710.USUARIO";
		sql+=" WHERE id_usuario = "+idusuario;


		PreparedStatement prepStmt = connection.prepareStatement(sql);
		resources.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		rs.next();
		
		return rs.getString("ROL").equals("Gerente");
	}
	
	
	//////////////////////////////////////////////////////////////////////
	///////////////RFC9. CONSULTAR ASISTENCIA A FESTIVANDES///////////////
    //////////////////////////////////////////////////////////////////////
	
	//Se quiere conocer la información de los usuarios que asistieron
	//al menos a una función de una determinada compañía en
	//un rango de fechas. Los resultados deben ser clasificados 
	//segun un criterio deseado por quien realiza la consulta. Debe
	//ofrecerse la posibilidad de agrupamiento y ordenamiento de
	//las respuestas según los intereses del usuario que consulta.
	
	public String consultarAsistencia(int idCompania, Date fechaInicio, Date fechaFin, String criterioOrdenamiento) throws SQLException
	{
		SimpleDateFormat dt1 = new SimpleDateFormat("yyyy/mm/dd");
		String formatoI = dt1.format(fechaInicio);
		String formatoF = dt1.format(fechaFin);
		String sql = "SELECT id_usuario, nombre_usuario, email ";
	    sql += "FROM usuario NATURAL INNER JOIN ((boleta NATURAL INNER JOIN funcion) NATURAL INNER JOIN realizado_por) ";
		sql += "WHERE asistio =  1 ";	
		sql += "AND id_usuario IS NOT NULL "; 
		sql += "AND id_compania = "+idCompania;
		sql += "AND fecha > TO_DATE('"+formatoI+", 'YYYY/MM/DD') ";
		sql += "AND fecha < TO_DATE('"+formatoF+", 'YYYY/MM/DD') ";
		sql += "GROUP BY id_usuario, nombre_usuario, email ";
		if(criterioOrdenamiento!=null)
		{
			sql+="ORDER BY "+criterioOrdenamiento;
		}
		
		String rta="";
		PreparedStatement prepStmt = connection.prepareStatement(sql);
		resources.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		
		while(rs.next())
		{
			rta+="Id: "+rs.getString("id_usuario")+", Nombre: "+rs.getString("nombre_usuario")+", Email: "+rs.getString("email")+"/n";
		}
		
		return rta;
	}
	
	//////////////////////////////////////////////////////////////////////
	/////////RFC10. CONSULTAR ASISTENCIA A FESTIVANDES – RFC9-v2//////////
	//////////////////////////////////////////////////////////////////////

	//Se quiere conocer la información de los usuarios QUE NO 
	//asistieron al menos a una función de una determinada compañía
	//en un rango de fechas. Los resultados deben ser clasificados 
	//según un criterio deseado por quien realiza la consulta. Debe
	//ofrecerse la posibilidad de agrupamiento y ordenamiento de las 
	//respuestas según los intereses del usuario que consulta.

	public String consultarAsistencia2(int idCompania, Date fechaInicio, Date fechaFin, String criterioOrdenamiento) throws SQLException
	{
		SimpleDateFormat dt1 = new SimpleDateFormat("yyyy/mm/dd");
		String formatoI = dt1.format(fechaInicio);
		String formatoF = dt1.format(fechaFin);
		String sql = "SELECT id_usuario, nombre_usuario, email ";
		sql += "FROM usuario NATURAL INNER JOIN ((boleta NATURAL INNER JOIN funcion) NATURAL INNER JOIN realizado_por) ";
		sql += "WHERE asistio =  0 ";	
		sql += "AND id_usuario IS NOT NULL "; 
		sql += "AND id_compania = "+idCompania;
		sql += "AND fecha > TO_DATE('"+formatoI+", 'YYYY/MM/DD') ";
		sql += "AND fecha < TO_DATE('"+formatoF+", 'YYYY/MM/DD') ";
		sql += "GROUP BY id_usuario, nombre_usuario, email ";
		if(criterioOrdenamiento!=null)
		{
			sql+="ORDER BY "+criterioOrdenamiento;
		}

		String rta="";
		PreparedStatement prepStmt = connection.prepareStatement(sql);
		resources.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while(rs.next())
		{
			rta+="Id: "+rs.getString("id_usuario")+", Nombre: "+rs.getString("nombre_usuario")+", Email: "+rs.getString("email")+"/n";
		}

		return rta;
	}

	//////////////////////////////////////////////////////////////////////
	/////////////////RFC11. CONSULTAR COMPRAS DE BOLETAS//////////////////
	//////////////////////////////////////////////////////////////////////

	//Muestra la información de las boletas compradas por usuarios 
	//de acuerdo con las características de las funciones
	//correspondientes. Esta consulta puede ser filtrada por 
	//diferentes conceptos (rangos de fecha, elementos del escenario,
	//tipo de localidad, franja horaria y día de la semana) y se 
	//espera como resultado: nombre del espectáculo, fecha de la
	//función, sitio de la función, cantidad de boletas vendidas, 
	//cantidad de usuarios registrados, entre otros. Esta operación es
	//realizada el gerente general de FestivAndes.

	public String consultarCompra(int idUsuario, Date fechaInicio, Date fechaFin, String elementos, String localidad, String franjaHoraria) throws Exception
	{
		if(esGerenteGeneral(idUsuario))
		{
			String sql = "SELECT nombre_espectaculo, id_espectaculo, fecha, nombre_lugar, count(*) boletas_vendidas ";
			sql+="FROM boleta NATURAL INNER JOIN ((funcion NATURAL INNER JOIN espectaculo) NATURAL INNER JOIN lugar) ";
			sql+="WHERE disponible = 0 ";
			
			if(fechaInicio!=null && fechaFin!=null)
			{
				SimpleDateFormat dt1 = new SimpleDateFormat("yyyy/mm/dd");
				String formatoI = dt1.format(fechaInicio);
				String formatoF = dt1.format(fechaFin);

				sql += "AND fecha > TO_DATE('"+formatoI+", 'YYYY/MM/DD') ";
				sql += "AND fecha < TO_DATE('"+formatoF+", 'YYYY/MM/DD') ";
			}
			
			if(elementos!=null)
			{
				String[] e = elementos.split(", ");
				for(int i=0;i<e.length;i++)
				{
					sql+="AND condiciones_tecnicas LIKE '%"+e[i]+"%' ";
				}
			}
			
			if(localidad!=null)
			{
				sql+="AND nombre_localidad = '"+localidad+"' ";
			}
			
			if(franjaHoraria!=null)
			{
				String hora[]=franjaHoraria.split("-");
				
				//DATEPART(HOUR, GETDATE());
				//SELECT DATEPART(MINUTE, GETDATE());
			}

			String rta="";
			PreparedStatement prepStmt = connection.prepareStatement(sql);
			resources.add(prepStmt);
			ResultSet rs = prepStmt.executeQuery();

			while(rs.next())
			{
				rta+="Nombre espectaculo: "+rs.getString("nombre_espectaculo")+", Fecha de la función: "+rs.getString("fecha")+", Sitio de la función: "+rs.getString("nombre_lugar")+
					", Boletas vendidas: "+rs.getString("boletas_vendidas")+"/n";
			}
			//Falto dia semana y usuarios registrados

			return rta;
		}
		else
		{
			throw new Exception("El usuario no tiene autorización para realizar esta consulta");
		}
	}

	//////////////////////////////////////////////////////////////////////
	/////////////////RFC12. CONSULTAR LOS BUENOS CLIENTES/////////////////
	//////////////////////////////////////////////////////////////////////

	//Los buenos clientes son aquellos que compran siempre en 
	//localidad VIP y también son aquellos que compran al menos n
	//boletas durante el festival. Esta consulta retorna toda la 
	//informacion de dichos clientes. Esta operación es realizada
	//únicamente por el gerente general de FestivAndes

	public String consultarBuenosClientes(int idUsuario, int n) throws SQLException, Exception
	{
		if(esGerenteGeneral(idUsuario))
		{
			String sql = "SELECT id_usuario, nombre_usuario, email ";
			sql += "FROM usuario NATURAL INNER JOIN ((boleta NATURAL INNER JOIN funcion) NATURAL INNER JOIN localidad) ";	
			sql += "WHERE id_usuario IS NOT NULL "; 
			sql += "GROUP BY id_usuario, nombre_usuario, email ";
			sql += "HAVING count(*)>"+(n-1);

			String rta="";
			PreparedStatement prepStmt = connection.prepareStatement(sql);
			resources.add(prepStmt);
			ResultSet rs = prepStmt.executeQuery();

			while(rs.next())
			{
				rta+="Id: "+rs.getString("id_usuario")+", Nombre: "+rs.getString("nombre_usuario")+", Email: "+rs.getString("email")+"/n";
			}

			return rta;
		}
		else
		{
			throw new Exception("El usuario no tiene autorización para realizar esta consulta");
		}
	}
}