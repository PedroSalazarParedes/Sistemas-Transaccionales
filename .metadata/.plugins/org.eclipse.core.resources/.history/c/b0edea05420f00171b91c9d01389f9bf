package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vos.CompaniaTeatro;
import vos.Espectaculo;
import vos.ListaEspectaculos;

public class EspectaculoDAO {

	private ArrayList<Object> resources;
	private Connection connection;

	public EspectaculoDAO() {
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
	 * Método que agrega el espectaculo que entra como parámetro a la base de datos.
	 * @param espectaculo- el espectaculo a agregar. video !=  null
	 * <b> post: </b> se ha agregado el espetaculo a la base de datos en la transaction actual. pendiente que el festival master
	 * haga commit para que el espectaculo baje  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el video a la base de datos
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void addEspectaculo(Espectaculo espectaculo) throws SQLException, Exception {

		String sql = "INSERT INTO ESPECTACULO VALUES (";
		sql += espectaculo.getId() + ",'";
		sql += espectaculo.getName() + "',";
		sql += espectaculo.getDuration() + ",";
		sql += espectaculo.getCost() + ",'";
		sql += espectaculo.getParticipation() + "','";
		sql += espectaculo.getDescription() + "','";
		sql += espectaculo.getObjetivo() + "','";
		sql += espectaculo.getIdioma() + "','";
		sql += espectaculo.getTraduccion() + "','";
		sql += espectaculo.getReqtecnicos() + "',";
		sql += espectaculo.getIdcategoria() + ")";
		
		

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = connection.prepareStatement(sql);
		resources.add(prepStmt);
		prepStmt.executeQuery();

	}
	
	public void addRealizadoPor(Espectaculo espe, CompaniaTeatro comp) throws SQLException, Exception {

		String sql = "INSERT INTO REALIZADO_POR VALUES (";
		sql += espe.getId() + ",";
		sql += comp.getId() + ")";
		
		

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = connection.prepareStatement(sql);
		resources.add(prepStmt);
		prepStmt.executeQuery();
	
	}
	
	public ListaEspectaculos getEspectaculos() throws SQLException, Exception{
		List<Espectaculo> e = new ArrayList<>();
		String sql = "Select * From Espectaculo";

		//no sée!
		


		PreparedStatement prepStmt = connection.prepareStatement(sql);
		resources.add(prepStmt);
		prepStmt.executeQuery();

		
	}
}
