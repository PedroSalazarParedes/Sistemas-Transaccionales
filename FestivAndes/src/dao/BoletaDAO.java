package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import vos.Boleta;
import vos.BoletaDevolver;
import vos.BoletaMultiple;
import vos.Devolucion;
import vos.Funcion1;
import vos.Localidad;


public class BoletaDAO {

	private ArrayList<Object> resources;
	private Connection connection;

	public BoletaDAO() {
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
	
	
	public Devolucion devolverBoleta(BoletaDevolver boleta, Integer cliente, boolean abona) throws SQLException, Exception 
	{
		if(!esCliente(cliente))
		{
			throw new Exception("El usuario no está autorizado para realizar esta transacción.");
		}
		
		
		String sql ="";
		int abonada =0;
		double valor=0;
		if(abona)
		{
			abonada=1;
		}
		if(boleta.getIdlocalidad()==null)
		{
			sql = "UPDATE ISIS2304B241710.BOLETA SET ";
			sql += "disponible = 1,";
			sql +=" abonada = 0";
			sql+=" WHERE id_funcion = "+boleta.getIdfuncion();
			sql+=" AND valor = "+boleta.getValor();
			sql+=" AND disponible = 1";
			sql+=" AND num_boleta = "+boleta.getNumBoletaDevolver();
			
			valor=boleta.getValor();
		}
		else if(boleta.getNumfila()==null)
		{
			sql = "UPDATE ISIS2304B241710.BOLETA SET ";
			sql += "id_usuario = "+cliente+",";
			sql += "disponible = 0,";
			sql +=" abonada = "+abonada;
			sql+=" WHERE id_funcion = "+boleta.getIdfuncion();
			sql+=" AND valor = "+boleta.getValor();
			sql+=" AND id_localidad = "+boleta.getIdlocalidad();
			sql+=" AND disponible = 1";
			sql+=" AND num_boleta = "+boleta.getNumBoletaDevolver();
			
			valor=boleta.getValor();
		}
		else
		{
		sql = "UPDATE ISIS2304B241710.BOLETA SET ";
		sql += "id_usuario = "+cliente+",";
		sql += "disponible = 0,";
		sql +=" abonada = "+abonada;
		sql+=" WHERE id_funcion = "+boleta.getIdfuncion();
		sql+=" AND valor = "+boleta.getValor();
		sql+=" AND id_localidad = "+boleta.getIdlocalidad();
		sql+=" AND num_fila = "+boleta.getValor();
		sql+=" AND num_silla = "+boleta.getNumsilla();
		sql+=" AND disponible = 1";
		sql+=" AND num_boleta = "+boleta.getNumBoletaDevolver();
		
		valor=boleta.getValor();
		}

		//System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = connection.prepareStatement(sql);
		resources.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		if(!rs.next())
		{
			throw new Exception("Error al devolver la boleta");
		}
		
		if(abona)
		{
			valor*=0.8;
		}
		Devolucion devolucion = new Devolucion(boleta,valor);
		return devolucion;
	}

	public void registrarCompraAbono(Funcion1 f, Localidad l, int cliente ) throws Exception {

		if(!esCliente(cliente))
		{
			throw new Exception("El usuario no está autorizado para realizar esta transacción.");
		}
		else if(diasParaInicioFestival()<=21)
		{
			throw new Exception("Esta operación solo se puede realizar con más tres semanas de anticipación con respecto al inicio del festival.");
		}
		else
		{
			Boleta b=null;

			if(l!=null)
			{
				double valor = darValorBoleta(f.getId(), l.getId());
				Integer[] c = darNumSilla(f.getId(), l.getId());
				Integer numSilla=c[1];
				Integer numFila=c[0];
				b = new Boleta(f.getId(),valor,l.getId(),numFila,numSilla);
			}
			else
			{
				double valor = darValorBoleta(f.getId(), null);
				b = new Boleta(f.getId(),valor,null,null,null);
			}

			registrarBoletaComprada(b, cliente, true);

		}
		

	}
	/**
	 * Metodo que registra como comprada la boleta que entra como parÃ¡metro a la base de datos.
	 * @param boleta- el boleta a agregar. video !=  null
	 * <b> post: </b> se ha agregado el espectaculo a la base de datos en la transaction actual. pendiente que el festival master
	 * haga commit para que el boleta baje  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el video a la base de datos
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void registrarBoletaComprada(Boleta boleta, Integer cliente, boolean abonada) throws SQLException, Exception {

		if(esCliente(cliente))
		{
			String sql ="";
			int abonad =0;
			if(abonada)
			{
				abonad=1;
			}
			if(boleta.getIdlocalidad()==null && !tieneLocalidades(darIdlugar(boleta.getIdfuncion())))
			{
				sql = "UPDATE ISIS2304B241710.BOLETA SET ";
				sql += "id_usuario = "+cliente+",";
				sql += "disponible = 0";
				sql +=" abonada = "+abonad;
				sql+=" WHERE id_funcion = "+boleta.getIdfuncion();
				sql+=" AND valor = "+boleta.getValor();
				sql+=" AND disponible = 1";
				sql+=" AND num_boleta = "+"(SELECT MIN(num_boleta) FROM ISIS2304B241710.BOLETA"
						+" WHERE id_funcion = "+boleta.getIdfuncion()
						+" AND valor = "+boleta.getValor()
						+" AND id_localidad = "+boleta.getIdlocalidad()
						+" AND num_fila = "+boleta.getValor()
						+" AND num_silla = "+boleta.getNumsilla()
						+" AND disponible = 1";
			}
			else if(boleta.getIdlocalidad()!=null && boleta.getNumfila()==null && tieneLocalidades(darIdlugar(boleta.getIdfuncion())))
			{
				sql = "UPDATE ISIS2304B241710.BOLETA SET ";
				sql += "id_usuario = "+cliente+",";
				sql += "disponible = 0,";
				sql +=" abonada = "+abonad;
				sql+=" WHERE id_funcion = "+boleta.getIdfuncion();
				sql+=" AND valor = "+boleta.getValor();
				sql+=" AND id_localidad = "+boleta.getIdlocalidad();
				sql+=" AND disponible = 1";
				sql+=" AND num_boleta = "+"(SELECT MIN(num_boleta) FROM ISIS2304B241710.BOLETA"
						+" WHERE id_funcion = "+boleta.getIdfuncion()
						+" AND valor = "+boleta.getValor()
						+" AND id_localidad = "+boleta.getIdlocalidad()
						+" AND num_fila = "+boleta.getValor()
						+" AND num_silla = "+boleta.getNumsilla()
						+" AND disponible = 1";
			}
			else if(boleta.getIdlocalidad()!=null && boleta.getNumfila()!=null && tieneLocalidades(darIdlugar(boleta.getIdfuncion())) && estaNumerada(boleta.getIdlocalidad()))
			{
				sql = "UPDATE ISIS2304B241710.BOLETA SET ";
				sql += "id_usuario = "+cliente+",";
				sql += "disponible = 0,";
				sql +=" abonada = "+abonad;
				sql+=" WHERE id_funcion = "+boleta.getIdfuncion();
				sql+=" AND valor = "+boleta.getValor();
				sql+=" AND id_localidad = "+boleta.getIdlocalidad();
				sql+=" AND num_fila = "+boleta.getValor();
				sql+=" AND num_silla = "+boleta.getNumsilla();
				sql+=" AND disponible = 1";
				sql+=" AND num_boleta = "+"(SELECT MIN(num_boleta) FROM ISIS2304B241710.BOLETA"
						+" WHERE id_funcion = "+boleta.getIdfuncion()
						+" AND valor = "+boleta.getValor()
						+" AND id_localidad = "+boleta.getIdlocalidad()
						+" AND num_fila = "+boleta.getValor()
						+" AND num_silla = "+boleta.getNumsilla()
						+" AND disponible = 1";
			}
			else
			{
				throw new Exception("Error al agregar la boleta.");
			}

			//System.out.println("SQL stmt:" + sql);

			PreparedStatement prepStmt = connection.prepareStatement(sql);
			resources.add(prepStmt);
			ResultSet rs = prepStmt.executeQuery();
			if(!rs.next())
			{
				throw new Exception("No hay boletas disponibles.");
			}
		}
		else
		{
			throw new Exception("El usuario no está autorizado para realizar esta transacción.");
		}
	}
	
	public void registrarBoletasCompradas(BoletaMultiple b, int id) throws Exception
	{
		int numBoletas = b.getNumboletas();
		
		Long idlocalidad = b.getIdlocalidad();
		Long idfuncion = b.getIdfuncion();
		double valor = b.getValor();
		Long idlugar=darIdlugar(idfuncion);
		
		if((idlocalidad==null && !tieneLocalidades(darIdlugar(idfuncion))) || (idlocalidad!=null && tieneLocalidades(idlugar) && !estaNumerada(idlugar)))
		{
			for(int i=0; i<numBoletas; i++)
			{
				registrarBoletaComprada(new Boleta(idfuncion, valor, idlocalidad, null, null), id, false);
			}
		}
		else
		{
			int [] sillas = sillasContiguas(numBoletas,idfuncion, idlocalidad);
			
			for(int i=0;i<numBoletas;i++)
			{
				registrarBoletaComprada(new Boleta(idfuncion, valor, idlocalidad, sillas[0], sillas[1]-i), id, false);
			}
		}
		
	}
	
	public void cancelar(Long idfuncion, int cliente) throws SQLException, Exception
	{
		if(!esAdministrador(cliente))
		{
			throw new Exception("El usuario no está autorizado para realizar esta transacción.");
		}
		String sql = "SELECT * FROM ISIS2304B241710.BOLETA";
		sql+=" WHERE id_funcion = "+idfuncion;
		sql+=" AND disponible = 0";
		
		PreparedStatement prepStmt = connection.prepareStatement(sql);
		resources.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while(rs.next())
		{
			Integer i = Integer.parseInt(rs.getString("ID_USUARIO"));
			if(i!=null)
			{
				boolean abonada=rs.getBoolean("ABONADA");
				devolverBoleta(new BoletaDevolver(Long.parseLong(rs.getString("ID_FUNCION")),rs.getDouble("VALOR"), Long.parseLong(rs.getString("ID_LOCALIDAD")), Integer.parseInt(rs.getString("NUM_FILA")),Integer.parseInt(rs.getString("NUM_SILLA")),rs.getInt("NUM_BOLETA")),i,abonada);
			}
		}
	}
	
	public double darValorBoleta(Long idfuncion, Long idlocalidad) throws SQLException
	{
		String sql="";
		if(idlocalidad==null)
		{
			sql = "SELECT * FROM ISIS2304B241710.BOLETA";
			sql+=" WHERE id_funcion = "+idfuncion;
		}
		else
		{
			sql = "SELECT * FROM ISIS2304B241710.BOLETA";
			sql+=" WHERE id_funcion = "+idfuncion;
			sql+=" AND id_localidad = "+idlocalidad;
		}
		
		PreparedStatement prepStmt = connection.prepareStatement(sql);
		resources.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		
		rs.next();
		
		return rs.getDouble("VALOR");
	}
	
	public Integer[] darNumSilla(Long idfuncion, Long idlocalidad) throws SQLException
	{
		Integer[] cosito = new Integer[2];
		String sql="";

		sql = "SELECT * FROM ISIS2304B241710.BOLETA";
		sql+=" WHERE id_funcion = "+idfuncion;
		sql+=" AND id_localidad = "+idlocalidad;
		sql+=" AND disponible = 1";

		PreparedStatement prepStmt = connection.prepareStatement(sql);
		resources.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		rs.next();

		cosito[0]=Integer.parseInt(rs.getString("NUM_FILA"));
		cosito[1]=Integer.parseInt(rs.getString("NUM_SILLA"));
		
		return cosito;
	}

	public boolean tieneLocalidades(Long idlugar) throws SQLException
	{
		String sql="";

		sql = "SELECT * FROM ISIS2304B241710.LUGAR NATURAL INNER JOIN ISIS2304B241710.LOCALIDAD";
		sql+=" WHERE id_lugar = "+idlugar;

		PreparedStatement prepStmt = connection.prepareStatement(sql);
		resources.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		return rs.next();
	}
	
	public boolean estaNumerada(Long idlugar) throws SQLException
	{
		String sql="";

		sql = "SELECT * FROM ISIS2304B241710.LUGAR NATURAL INNER JOIN ISIS2304B241710.LOCALIDAD";
		sql+=" WHERE id_lugar = "+idlugar;

		PreparedStatement prepStmt = connection.prepareStatement(sql);
		resources.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next())
		{
			if(rs.getString("NUM_FILAS")!=null)
			{
				return true;
			}
		}
		return false;
	}
	
	public Long darIdlugar(Long idfuncion) throws SQLException
	{
		String sql="";

		sql = "SELECT * FROM ISIS2304B241710.FUNCION";
		sql+=" WHERE id_funcion = "+idfuncion;


		PreparedStatement prepStmt = connection.prepareStatement(sql);
		resources.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		rs.next();
		
		return rs.getLong("ID_LUGAR");
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
	
	public int diasParaInicioFestival() throws SQLException
	{
		Calendar c = Calendar.getInstance();
		
		String sql="";

		sql = "SELECT * FROM ISIS2304B241710.FESTIVANDES";

		PreparedStatement prepStmt = connection.prepareStatement(sql);
		resources.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		rs.next();
		Date hoy = c.getTime();
		Date fecha = rs.getDate("FECHA_INICIO");
		
		return (int)(fecha.getTime()-hoy.getTime());
	}
	
	public int diasParaFuncion(Long idfuncion) throws SQLException
	{
		Calendar c = Calendar.getInstance();
		
		String sql="";

		sql = "SELECT * FROM ISIS2304B241710.FUNCION";
		sql+=" WHERE id_funcion = "+idfuncion;

		PreparedStatement prepStmt = connection.prepareStatement(sql);
		resources.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		rs.next();
		Date hoy = c.getTime();
		Date fecha = rs.getDate("FECHA");
		
		return (int)(fecha.getTime()-hoy.getTime());
	}
	
	public int[] sillasContiguas(int numsillas, Long idfuncion, Long idlocalidad) throws SQLException, Exception
	{
		int[] arreglo = new int[2];
		
		String sql = "SELECT * FROM ISIS2304B241710.BOLETA";
		sql+=" WHERE id_funcion = "+idfuncion;
		sql+=" AND id_localidad = "+idlocalidad;
		sql+=" AND disponible = 1";
		
		PreparedStatement prepStmt = connection.prepareStatement(sql);
		resources.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next())
		{
			int contador=1;
			int numFila=rs.getInt("NUM_FILA");
			int numSilla=rs.getInt("NUM_SILLA");

			while(rs.next())
			{
				int fila = rs.getInt("NUM_FILA");
				int silla = rs.getInt("NUM_SILLA");
				if(fila==numFila && silla==(numSilla+1))
				{
					contador++;
					numSilla=silla;
				}
				else
				{
					contador=1;
					numFila=fila;
					numSilla=silla;
				}
				
				if(contador==numsillas)
				{
					arreglo[0]=numFila;
					arreglo[1]=numSilla;
					return arreglo;
				}
			}
		}
		
		throw new Exception("No hay suficientes sillas contiguas disponibles.");
	}
}