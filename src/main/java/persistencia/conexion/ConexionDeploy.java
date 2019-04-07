package persistencia.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDeploy {
	private static ConexionDeploy instancia;
	private Connection connection;
	private static String DBURL;
	private static String DBUSR;
	private static String DBPASS;
	private boolean conectable;
	
	private ConexionDeploy()	{
		DBURL = "jdbc:mysql://localhost:3306";		
		DBUSR = "root";
		DBPASS = "pass";
		
		try		{
			Class.forName("com.mysql.jdbc.Driver"); // quitar si no es necesario			
			this.connection = DriverManager.getConnection(DBURL, DBUSR, DBPASS);
			conectable = true;
		}
		catch(Exception e)		{
			conectable = false;
		}		
	}	
	
	public static ConexionDeploy getConexion()	{								
		if(instancia == null)	{
			instancia = new ConexionDeploy();
		}
		return instancia;
	}

	public Connection getSQLConexion()	{
		return this.connection;
	}
	
	public void setURL() {
		DBURL = "jdbc:mysql://localhost:3306/agenda";
		cerrarConexion();		
		try		{
			Class.forName("com.mysql.jdbc.Driver"); // quitar si no es necesario			
			connection = DriverManager.getConnection(DBURL, DBUSR, DBPASS);
		}
		catch(Exception e)		{
		}		
		
		
	}
	
	public void cerrarConexion(){
		if (conectable) {
			try {
				this.connection.close();
			}
			catch (SQLException e)	{
			}
			instancia = null;	
		}		
	}	
	
	private boolean getConectable() {
		return conectable;
	}
	
	public static boolean testConexion() {
		instancia = new ConexionDeploy();
		return instancia.getConectable();
	}
}
