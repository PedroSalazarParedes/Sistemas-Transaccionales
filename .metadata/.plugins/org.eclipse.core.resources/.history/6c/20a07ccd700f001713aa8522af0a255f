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
	

	public void addCompania(CompaniaTeatro compania) throws SQLException, Exception {

		String sql = "INSERT INTO COMPA�IA_TEATRO VALUES (";
		sql += compania.getId() + ",'";
		sql += compania.getName() + "','";
		sql += compania.getRepresentante() + "','";
		sql += compania.getPaginaWeb() + "','";
		sql += compania.getPais() + "')";
		

		//System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = connection.prepareStatement(sql);
		resources.add(prepStmt);
		prepStmt.executeQuery();

	}
	
}