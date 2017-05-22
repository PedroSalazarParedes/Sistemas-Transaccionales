package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

		String sql = "INSERT INTO ISIS2304B241710.USUARIO VALUES (";
		sql += usuario.getId() + ",'";
		sql += usuario.getName() + "','";
		sql += usuario.getEmail() + "','";
		sql += usuario.getRol() + "')";
		
		

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = connection.prepareStatement(sql);
		resources.add(prepStmt);
		prepStmt.executeQuery();

	}
	
	public void agregarUsuarios() throws SQLException
	{
		for (int i=1;i<=250;i++)
		{
			int j=10+i;
			String sql="insert into boleta values("+i+",123,10000,null,null,null,0,"+j+",0,1)";
			PreparedStatement prepStmt = connection.prepareStatement(sql);
			resources.add(prepStmt);
			prepStmt.executeQuery();
			
			
		}
	}
	
	public void agregarUsuarios(int num_funcion) throws SQLException
	{
		for (int i=251;i<=500;i++)
		{
			int j=10+i;
			String sql="insert into boleta values("+i+","+num_funcion+",10000,null,null,null,1,null,0,1)";
			PreparedStatement prepStmt = connection.prepareStatement(sql);
			resources.add(prepStmt);
			prepStmt.executeQuery();
			
			
		}
	}
	public Usuario darUsuario(Integer id) throws SQLException, Exception {

		String sql = "SELECT * FROM ISIS2304B241710.USUARIO"
				+" WHERE id_usuario = "+id;
		

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = connection.prepareStatement(sql);
		resources.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		
		while(rs.next())
		{
			Usuario u = new Usuario(rs.getLong("id_usuario"),rs.getString("nombre_cliente"),rs.getString("email"),rs.getString("rol"));
			return u;
		}
		
		return null;

	}
	

}

