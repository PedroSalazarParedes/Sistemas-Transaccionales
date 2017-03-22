package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Boleta;

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
	
	/**
	 * Método que registra como comprada la boleta que entra como parámetro a la base de datos.
	 * @param boleta- el boleta a agregar. video !=  null
	 * <b> post: </b> se ha agregado el espectaculo a la base de datos en la transaction actual. pendiente que el festival master
	 * haga commit para que el boleta baje  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el video a la base de datos
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void registrarBoletaComprada(Boleta boleta, int cliente) throws SQLException, Exception {

		String sql = "UPDATE ISIS2304B241710.BOLETA SET ";
		sql += "cliente = "+cliente+",";
		sql += "disponible = 0";
		sql+="WHERE id_funcion = "+boleta.getIdfuncion();
		sql+=" AND valor = "+boleta.getValor();
		sql+=" AND id_localidad = "+boleta.getIdlocalidad();
		sql+=" AND num_fila = "+boleta.getValor();
		sql+=" AND num_silla = "+boleta.getNumsilla();
		sql+=" AND disponible = 1";
		sql+=" AND num_boleta = "+"(SELECT MIN(num_boleta) FROM ISIS2304B241710.BOLETA"
				                             +"WHERE id_funcion = "+boleta.getIdfuncion()
			                                 +" AND valor = "+boleta.getValor()
				                             +" AND id_localidad = "+boleta.getIdlocalidad()
				                             +" AND num_fila = "+boleta.getValor()
				                             +" AND num_silla = "+boleta.getNumsilla()
				                             +" AND disponible = 1";

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = connection.prepareStatement(sql);
		resources.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		if(!rs.next())
		{
			throw new Exception("No se registr� correctamente la compra de la boleta");
		}
	}


	

}