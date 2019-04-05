package persistencia.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import utils.ConfigFile;

public class Conexion{
	private static Conexion instancia;
	private Connection connection;
	private Logger log = Logger.getLogger(Conexion.class);
	private static String DBURL;
	private static String DBUSR;
	private static String DBPASS;
	private boolean conectable;
	
	private Conexion()	{
		DBURL = "jdbc:mysql://" + ConfigFile.getInstance().getURL() + "/agenda";		
		DBUSR = ConfigFile.getInstance().getUSR();
		DBPASS = ConfigFile.getInstance().getPWD();
		
		try		{
			Class.forName("com.mysql.jdbc.Driver"); // quitar si no es necesario			
			this.connection = DriverManager.getConnection(DBURL, DBUSR, DBPASS);
			log.info("Conexión exitosa");
			conectable = true;
		}
		catch(Exception e)		{
			log.error("Conexión fallida", e);
			conectable = false;
		}		
	}	
	
	public static Conexion getConexion()	{								
		if(instancia == null)	{
			instancia = new Conexion();
		}
		return instancia;
	}

	public Connection getSQLConexion()	{
		return this.connection;
	}
	
	public void cerrarConexion(){
		if (conectable) {
			try {
				this.connection.close();
				log.info("Conexion cerrada");
			}
			catch (SQLException e)	{
				log.error("Error al cerrar la conexión!", e);
			}
			instancia = null;	
		}		
	}	
	
	private boolean getConectable() {
		return conectable;
	}
	
	public static boolean testConexion() {
		instancia = new Conexion();
		return instancia.getConectable();
	}
}