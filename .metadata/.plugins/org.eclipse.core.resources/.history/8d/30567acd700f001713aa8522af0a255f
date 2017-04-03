package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Localidad;
import vos.Lugar;

public class LugarDAO {

	private ArrayList<Object> resources;
	private Connection connection;

	public LugarDAO() {
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
	

	public void addLugar(Lugar lugar) throws SQLException, Exception {

		String sql = "INSERT INTO LUGAR VALUES (";
		sql += lugar.getId() + ",'";
		sql += lugar.getName() + "','";
		sql += lugar.getTipo() + "',";
		sql += lugar.getCapacity()+ ",'";
		sql += lugar.getSilleteria() + "','";
		sql += lugar.getAccesibilidad() + "',";
		sql += lugar.getProteccion()+ ",'";
		sql += lugar.getCondtecnicas() + "')";
		

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = connection.prepareStatement(sql);
		resources.add(prepStmt);
		prepStmt.executeQuery();

	}
	
	

	public Lugar buscarLugarPorId(Long id) throws SQLException, Exception {
		Lugar lug = null;

		String sql = "SELECT * FROM LUGAR WHERE ID_LUGAR =" + id ;

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = connection.prepareStatement(sql);
		resources.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			Long id2 = Long.parseLong(rs.getString("ID_LUGAR"));
			String name2 = rs.getString("NOMBRE_LUGAR");
			String tipo = rs.getString("TIPO");
			int capacidad = Integer.parseInt(rs.getString("CAPACIDAD"));
			String tiposillas = rs.getString("TIPO_SILLETERIA");
			String access = rs.getString("ACCESIBILLIDAD");
			int protec = Integer.parseInt(rs.getString("PROTECCION"));
			String cond = rs.getString("CONDICIONES_TECNICAS");
			lug = new Lugar(id2, name2, tipo, capacidad, tiposillas, access, protec, cond);
		}
		
		return lug;

	}
	
	public void addLocalidad(Localidad loc) throws SQLException, Exception {

		String sql = "INSERT INTO LOCALIDAD VALUES (";
		sql += loc.getId() + ",'";
		sql += loc.getName() + "',";
		sql += loc.getIdlugar() + ",";
		sql += loc.getCapacity()+ ",";
		sql += loc.getNumfilas() + ",";
		sql += loc.getNumsillas() + ")";
		

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = connection.prepareStatement(sql);
		resources.add(prepStmt);
		prepStmt.executeQuery();

	}
	
	public Localidad buscarLocalidadPorNombreYLugar(String name, Long idlugar) throws SQLException, Exception {
		Localidad lug = null;

		String sql = "SELECT * FROM LOCALIDAD WHERE ID_LUGAR =" + idlugar + " AND NOMBRE_LOCALIDAD='"+name+"'" ;

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = connection.prepareStatement(sql);
		resources.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			Long id = Long.parseLong(rs.getString("ID_LOCALIDAD"));
			String name2 = rs.getString("NOMBRE_LOCALIDAD");
			Long idlug = Long.parseLong(rs.getString("ID_LUGAR"));
			int capacidad = Integer.parseInt(rs.getString("CAPACIDAD"));
			int numfilas = Integer.parseInt(rs.getString("NUM_FILAS"));
			int numsillas = Integer.parseInt(rs.getString("NUM_SILLAS"));
			lug = new Localidad(id, name2, idlug, capacidad, numfilas, numsillas);
		}
		
		return lug;

	}
	
	
	
}