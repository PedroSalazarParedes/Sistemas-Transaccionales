package dtm;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TopicConnectionFactory;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import com.rabbitmq.jms.admin.RMQConnectionFactory;
import com.rabbitmq.jms.admin.RMQDestination;

import jms.ConsultaFuncionesDisponiblesMDB;
import tm.FestivAndesMaster;
import vos.ListaFunciones;

public class FestivAndesDistributed 
{
	private final static String QUEUE_NAME = "java:global/RMQAppQueue";
	private final static String MQ_CONNECTION_NAME = "java:global/RMQClient";
	
	private static FestivAndesDistributed instance;
	
	private FestivAndesMaster tm;
	
	private QueueConnectionFactory queueFactory;
	
	private TopicConnectionFactory factory;
	
	private ConsultaFuncionesDisponiblesMDB funcionMQ;
	
	private static String path;


	private FestivAndesDistributed() throws NamingException, JMSException
	{
		InitialContext ctx = new InitialContext();
		factory = (RMQConnectionFactory) ctx.lookup(MQ_CONNECTION_NAME);
		funcionMQ = new ConsultaFuncionesDisponiblesMDB(factory, ctx);
		
		funcionMQ.start();
		
	}
	
	public void stop() throws JMSException
	{
		funcionMQ.close();
	}
	
	/**
	 * Método que retorna el path de la carpeta WEB-INF/ConnectionData en el deploy actual dentro del servidor.
	 * @return path de la carpeta WEB-INF/ConnectionData en el deploy actual.
	 */
	public static void setPath(String p) {
		path = p;
	}
	
	public void setUpTransactionManager(FestivAndesMaster tm)
	{
	   this.tm = tm;
	}
	
	private static FestivAndesDistributed getInst()
	{
		return instance;
	}
	
	public static FestivAndesDistributed getInstance(FestivAndesMaster tm)
	{
		if(instance == null)
		{
			try {
				instance = new FestivAndesDistributed();
			} catch (NamingException e) {
				e.printStackTrace();
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		instance.setUpTransactionManager(tm);
		return instance;
	}
	
	public static FestivAndesDistributed getInstance()
	{
		if(instance == null)
		{
			FestivAndesMaster tm = new FestivAndesMaster(path);
			return getInstance(tm);
		}
		if(instance.tm != null)
		{
			return instance;
		}
		FestivAndesMaster tm = new FestivAndesMaster(path);
		return getInstance(tm);
	}
	
	public ListaFunciones getLocalFunciones() throws Exception
	{
		return tm.darFuncionesLocal();
	}
	
	public ListaFunciones getRemoteFunciones() throws JsonGenerationException, JsonMappingException, JMSException, IOException, NonReplyException, InterruptedException, NoSuchAlgorithmException
	{
		return funcionMQ.getRemoteFunciones();
	}

	
}
