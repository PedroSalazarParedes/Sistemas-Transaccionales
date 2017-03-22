package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;


import vos.Usuario;

public class UsuarioDAO {

	private ArrayList<Object> resources;
	private Connection connection;

	public UsuarioDAO() {
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
	

	public void addUsuario(Usuario usuario) throws SQLException, Exception {

		String sql = "INSERT INTO USUARIO VALUES (";
		sql += usuario.getId() + ",'";
		sql += usuario.getName() + "','";
		sql += usuario.getEmail() + "','";
		sql += usuario.getRol() + "')";
		
		

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = connection.prepareStatement(sql);
		resources.add(prepStmt);
		prepStmt.executeQuery();

	}
	

}

