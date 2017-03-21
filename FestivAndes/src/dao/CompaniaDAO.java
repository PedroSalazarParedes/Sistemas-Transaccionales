package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.CompaniaTeatro;

public class CompaniaDAO {

	private ArrayList<Object> resources;
	private Connection connection;

	public CompaniaDAO() {
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
	 * MÃ©todo que agrega el espectaculo que entra como parÃ¡metro a la base de datos.
	 * @param espectaculo- el espectaculo a agregar. video !=  null
	 * <b> post: </b> se ha agregado el espetaculo a la base de datos en la transaction actual. pendiente que el festival master
	 * haga commit para que el espectaculo baje  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el video a la base de datos
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void addCompania(CompaniaTeatro compañia) throws SQLException, Exception {

		String sql = "INSERT INTO COMPAÑIA_TEATRO VALUES (";
		sql += compañia.getId() + ",'";
		sql += compañia.getName() + "','";
		sql += compañia.getRepresentante() + "','";
		sql += compañia.getPaginaWeb() + "','";
		sql += compañia.getPais() + "')";
		

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = connection.prepareStatement(sql);
		resources.add(prepStmt);
		prepStmt.executeQuery();

	}
	
}