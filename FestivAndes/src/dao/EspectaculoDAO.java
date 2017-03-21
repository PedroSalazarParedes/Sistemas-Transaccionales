package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

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
	
	
	

}
