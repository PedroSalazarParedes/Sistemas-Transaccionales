package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Espectaculo;

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
	 public void addEspectaculo(Espectaculo e) throws SQLException,Exception{
		 
		 String sql = "ACÁ VA LA SENTENCIA";
		 
		 //acá hay que unir la sentencia con la info de el espectaculo que le pasan por parametro
		 //como poniendo sql += e.getId() + ",'" etc etc
		 

			PreparedStatement prepStmt = connection.prepareStatement(sql);
			resources.add(prepStmt);
			prepStmt.executeQuery();

		 
	 }
	
	

}