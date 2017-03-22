package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Funcion;

public class FuncionDAO {

	private ArrayList<Object> resources;
	private Connection connection;

	public FuncionDAO() {
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
	 * Método que agrega el funcion que entra como parámetro a la base de datos.
	 * @param funcion- el funcion a agregar. video !=  null
	 * <b> post: </b> se ha agregado el espetaculo a la base de datos en la transaction actual. pendiente que el festival master
	 * haga commit para que el funcion baje  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el video a la base de datos
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void addFuncion(Funcion funcion) throws SQLException, Exception {

		String sql = "INSERT INTO ISIS2304B241710.FUNCION VALUES (";
		sql += funcion.getId() + ",'";
		sql += funcion.getFecha() + "','";
		sql += funcion.getHora() + "',";
		sql += 0 + ",";
		sql += "null ,";
		sql += funcion.getIdlugar() + ",";
		sql += funcion.getIdespectaculo() + ")";
		
		

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = connection.prepareStatement(sql);
		resources.add(prepStmt);
		prepStmt.executeQuery();

	}
	
	public void registrarFuncionRealizada(Long idfuncion) throws SQLException, Exception{
		
		
		String sql = "UPDATE ISIS2304B241710.FUNCION SET ";
		sql += "realizado=1,";
		sql += "num_asistentes= (SELECT COUNT(*) FROM ISIS2304B241710.BOLETA"
				                                + "WHERE id_funcion = "+idfuncion
				                                + "AND disponible = 0)";
		sql += " WHERE id = " + idfuncion;

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = connection.prepareStatement(sql);
		resources.add(prepStmt);
		prepStmt.executeQuery();
		
	}
	
	public void addBoleta(Funcion espe, int valor, int num) throws SQLException, Exception {

		Long idfuncion = espe.getId();
		String n = "NULL";
		
		String sql = "INSERT INTO ISIS2304B241710.BOLETA VALUES (";
		sql += num + ",";
		sql += idfuncion + ",";
		sql += valor + ",";
		sql += n + ",";
		sql += n + ",";
		sql += n + ",";
		sql += n + ",";
		sql += "1)";
		
		

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = connection.prepareStatement(sql);
		resources.add(prepStmt);
		prepStmt.executeQuery();
	
	}
	
	public void addBoletaLocalidad(Funcion espe, int valor, int num, Long idlocalidad) throws SQLException, Exception
	{
		Long idfuncion = espe.getId();
		String n = "NULL";
		
		String sql = "INSERT INTO ISIS2304B241710.BOLETA VALUES (";
		sql += num + ",";
		sql += idfuncion + ",";
		sql += valor + ",";
		sql += n + ",";
		sql += idlocalidad + ",";
		sql += n + ",";
		sql += n + ",";
		sql += "1)";
		
		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = connection.prepareStatement(sql);
		resources.add(prepStmt);
		prepStmt.executeQuery();
	}
	
	public void addBoletaNumerada(Funcion espe, int valor, int num, Long idlocalidad, int numfila, int numsilla)throws SQLException, Exception
	{
		Long idfuncion = espe.getId();
		String n = "NULL";
		
		String sql = "INSERT INTO ISIS2304B241710.BOLETA VALUES (";
		sql += num + ",";
		sql += idfuncion + ",";
		sql += valor + ",";
		sql += n + ",";
		sql += idlocalidad + ",";
		sql += numfila + ",";
		sql += numsilla + ",";
		sql += "1)";
		
		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = connection.prepareStatement(sql);
		resources.add(prepStmt);
		prepStmt.executeQuery();
	}
	
	
	
	
	
	
}