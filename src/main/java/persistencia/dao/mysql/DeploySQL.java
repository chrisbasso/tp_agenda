package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import persistencia.conexion.ConexionDeploy;

public class DeploySQL {
	private static final ConexionDeploy conexion = ConexionDeploy.getConexion();
	
	public static final String dropDB = "DROP DATABASE IF EXISTS agenda";
	public static final String create = "CREATE DATABASE agenda";
	public static final String contacto = "CREATE TABLE tipo_contacto" +
	"(" +
	  "idTipoContacto int(10) NOT NULL AUTO_INCREMENT,"+
	  "tipo varchar(20)," +
	  "PRIMARY KEY (idTipoContacto)" +
	")";

	public static final String localidad = "CREATE TABLE localidad" +
	"(" +
	  "idLocalidad int(10) NOT NULL AUTO_INCREMENT," + 
	  "nombre_localidad varchar(20)," +
	  "PRIMARY KEY (idLocalidad)" +
	")";

	public static final String domicilio = "CREATE TABLE domicilio" +
	"(" +
	  "idDomicilio int(10) NOT NULL AUTO_INCREMENT," +
	  "calle varchar(20),"+
	  "altura varchar(10),"+
	  "piso varchar(10),"+
	  "depto varchar(20),"+
	  "idLocalidad int(10),"+
	  "PRIMARY KEY (idDomicilio),"+
	  "FOREIGN KEY (idLocalidad) REFERENCES localidad (idLocalidad)"+
	")";

	public static final String persona = "CREATE TABLE persona" +
	"(" +
	  "idPersona int(10) NOT NULL AUTO_INCREMENT,"+
	  "nombre varchar(45) NOT NULL,"+
	  "apellido varchar(45) NOT NULL,"+
	  "telefono varchar(20) NOT NULL,"+
	  "PRIMARY KEY (idPersona),"+
	  "idDomicilio int(10),"+
	  "idTipoContacto int(10),"+
	  "email varchar(80),"+
	  "fecha_nacimiento DATE,"+
	  "FOREIGN KEY (idTipoContacto) REFERENCES tipo_contacto (idTipoContacto),"+
	  "FOREIGN KEY (idDomicilio) REFERENCES domicilio (idDomicilio)"+
	")";

	public static void executeCommand (String command) {
		PreparedStatement statement;
		try	{
			statement = conexion.getSQLConexion().prepareStatement(command);
			statement.executeUpdate();
				
		}catch (SQLException e)	{
			e.printStackTrace();
		}
	}
	
	public static void useDB() {
		conexion.setURL();
	}
}