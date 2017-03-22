package tm;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import dao.BoletaDAO;
import dao.CompaniaDAO;
import dao.ConsultasDAO;
import dao.EspectaculoDAO;
import dao.FuncionDAO;
import dao.LugarDAO;
import dao.UsuarioDAO;
import vos.Boleta;
import vos.CompaniaTeatro;
import vos.Espectaculo;
import vos.Funcion;
import vos.ListaCompanias;
import vos.ListaEspectaculos;
import vos.ListaLocalidades;
import vos.Localidad;
import vos.Lugar;
import vos.Usuario;

public class FestivAndesMaster {
	
	/**
	 * Atributo estático que contiene el path relativo del archivo que tiene los datos de la conexión
	 */
	private static final String CONNECTION_DATA_FILE_NAME_REMOTE = "/conexion.properties";

	/**
	 * Atributo estático que contiene el path absoluto del archivo que tiene los datos de la conexión
	 */
	private  String connectionDataPath;

	/**
	 * Atributo que guarda el usuario que se va a usar para conectarse a la base de datos.
	 */
	private String user;

	/**
	 * Atributo que guarda la clave que se va a usar para conectarse a la base de datos.
	 */
	private String password;

	/**
	 * Atributo que guarda el URL que se va a usar para conectarse a la base de datos.
	 */
	private String url;

	/**
	 * Atributo que guarda el driver que se va a usar para conectarse a la base de datos.
	 */
	private String driver;
	
	/**
	 * Conexión a la base de datos
	 */
	private Connection conn;


	/**
	 * Método constructor de la clase FestivAndesMaster, esta clase modela y contiene cada una de las 
	 * transacciones y la logia de negocios que estas conllevan.
	 * <b>post: </b> Se crea el objeto FestivAndesMaster, se inicializa el path absoluto de el archivo de conexión y se
	 * inicializa los atributos que se usan par la conexión a la base de datos.
	 * @param contextPathP - path absoluto en el servidor del contexto del deploy actual
	 */
	public FestivAndesMaster(String contextPathP) {
		connectionDataPath = contextPathP + CONNECTION_DATA_FILE_NAME_REMOTE;
		initConnectionData();
	}

	/*
	 * Método que  inicializa los atributos que se usan para la conexion a la base de datos.
	 * <b>post: </b> Se han inicializado los atributos que se usan par la conexión a la base de datos.
	 */
	private void initConnectionData() {
		try {
			File arch = new File(this.connectionDataPath);
			Properties prop = new Properties();
			FileInputStream in = new FileInputStream(arch);
			prop.load(in);
			in.close();
			this.url = prop.getProperty("url");
			this.user = prop.getProperty("usuario");
			this.password = prop.getProperty("clave");
			this.driver = prop.getProperty("driver");
			Class.forName(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método que  retorna la conexión a la base de datos
	 * @return Connection - la conexión a la base de datos
	 * @throws SQLException - Cualquier error que se genere durante la conexión a la base de datos
	 */
	private Connection darConexion() throws SQLException {
		System.out.println("Connecting to: " + url + " With user: " + user);
		return DriverManager.getConnection(url, user, password);
	}

	////////////////////////////////////////
	///////Transacciones////////////////////
	////////////////////////////////////////
	
	
	//RFC6
	public ListaEspectaculos getEspectaculos() throws Exception{
		EspectaculoDAO dao = new EspectaculoDAO();
		ArrayList<Espectaculo> lista = null;
		try 
		{
			//////Transacción
			this.conn = darConexion();
			dao.setConnection(conn);
			lista  = dao.getEspectaculos();
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				dao.closeResources();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return new ListaEspectaculos(lista);
	}

	////////////////////////////////////////
	///////Requerimientos///////////////////
	////////////////////////////////////////

	//RF4
	

	public void addEspectaculo(Espectaculo espectaculo, ListaCompanias list) throws Exception {
		EspectaculoDAO dao = new EspectaculoDAO();
		try 
		{
			//////Transacción
			this.conn = darConexion();
			conn.setAutoCommit(false);
			dao.setConnection(conn);
			dao.addEspectaculo(espectaculo);
			for(CompaniaTeatro c : list.getCompanias())
				dao.addRealizadoPor(espectaculo, c);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			conn.rollback();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			try {
				dao.closeResources();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	
	//RF1-2

	public void addUsuario(Usuario usuario) throws Exception {
		UsuarioDAO dao = new UsuarioDAO();
		try 
		{
			//////Transacción
			this.conn = darConexion();
			dao.setConnection(conn);
			dao.addUsuario(usuario);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				dao.closeResources();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	
	//RF3
	
	public void addCompaniaTeatro(CompaniaTeatro compania) throws Exception {
		CompaniaDAO dao = new CompaniaDAO();
		try 
		{
			//////Transacción ACID
			this.conn = darConexion();
			dao.setConnection(conn);
			dao.addCompania(compania);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				dao.closeResources();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	
	
	//RF5

	public void addLugar(Lugar lugar, ListaLocalidades localidades) throws Exception {
		LugarDAO dao = new LugarDAO();
		try 
		{
			//////Transacción ACID
			this.conn = darConexion();
			conn.setAutoCommit(false);
			dao.setConnection(conn);
			dao.addLugar(lugar);
			for(Localidad c : localidades.getLocalidades())
				dao.addLocalidad(c);
			
			conn.commit();
			
			
		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				dao.closeResources();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	
	
	//RF6
	
	public void addFuncion(Funcion fun, ArrayList<String> nomLocalidades, ArrayList<Integer> valores) throws Exception {
		FuncionDAO dao = new FuncionDAO();
		LugarDAO dao2 = new LugarDAO();
		try 
		{
			//////Transacción
			this.conn = darConexion();
			conn.setAutoCommit(false);
			dao.setConnection(conn);
			dao2.setConnection(conn);
			dao.addFuncion(fun);
			
			Long lugar = fun.getIdlugar();
			Lugar l = dao2.buscarLugarPorId(lugar);
			
			int capacidad=l.getCapacity();
			
			for(int num=1; num<=capacidad;)
			{
				if(l.getId()==null)
				{
					dao.addBoleta(fun, valores.get(0), num);
					num++;
				}
				else
				{
					for(int i=0;i<nomLocalidades.size();i++)
					{
						Localidad loc = dao2.buscarLocalidadPorNombreYLugar(nomLocalidades.get(i), lugar);
						Integer numfilas = loc.getNumfilas();
						Long locid = loc.getId();
						if(numfilas==null)
						{
							dao.addBoletaLocalidad(fun, valores.get(i), num, locid);
							num++;
						}
						else
						{
							for(int j=0;j<numfilas;j++)
							{
								Integer numsillas=loc.getNumsillas();
								for(int k=0;k<numsillas;k++)
								{
									dao.addBoletaNumerada(fun, valores.get(i), num, locid, j, k);
									num++;
								}
							}
						}
					}
				}
			}
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			conn.rollback();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			try {
				dao.closeResources();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	
	//RF9
	
	public void registrarFuncionRealizada(Long idfuncion) throws  Exception
	{
		FuncionDAO dao = new FuncionDAO();
		try 
		{
			//////Transacción
			this.conn = darConexion();
			dao.setConnection(conn);
			dao.registrarFuncionRealizada(idfuncion);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				dao.closeResources();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	
	//RF8
	
	public void registrarBoletaComprada(Boleta boleta, int cliente)throws Exception
	{
		BoletaDAO dao = new BoletaDAO();
		try 
		{
			//////Transaccion
			this.conn = darConexion();
			dao.setConnection(conn);
			dao.registrarBoletaComprada(boleta, cliente);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				dao.closeResources();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	
	
	//RF7


	////////////////////////////////////////
	///////Consultas////////////////////////
	////////////////////////////////////////
	
	//RFC1
	
	//RFC6
	public ListaEspectaculos darEspectaculosMasPopulares(String from, String to) throws SQLException, Exception {
		ConsultasDAO dao = new ConsultasDAO();
		try 
		{
			//////Transaccion
			this.conn = darConexion();
			dao.setConnection(conn);
			ListaEspectaculos list = new ListaEspectaculos(dao.getEspectaculosPopulares(from, to));
			conn.commit();
			return list;

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				dao.closeResources();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	

}

	
	