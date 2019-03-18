package deployment;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import persistencia.conexion.Conexion;

public class Mysql {
	
	private static Conexion conn = Conexion.getConexion();
	private static PreparedStatement prepStatement;
	
	public static void start() {
		createTables();
	}
	
	private static void createTables() {
		createTableTipoPersona();
		createTableLocalidad();		
		createTableDomicilio();
		createTablePersona();		 
	}

	private static void createTableTipoPersona() {
		String instruction = "CREATE TABLE tipo_persona("
		+ "idTipoPersona int(10) NOT NULL AUTO_INCREMENT,"
		+ "tipo varchar(20),"
		+ "PRIMARY KEY (idTipoPersona)"
		+ ");";
		
		try {
			prepStatement = conn.getSQLConexion().prepareStatement(instruction);
			prepStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	private static void createTableLocalidad() {
		String instruction = "CREATE TABLE localidad("
		+ "idLocalidad int(10) NOT NULL AUTO_INCREMENT,"  
		+ "nombre_localidad varchar(20),"  
		+ "PRIMARY KEY (idLocalidad)"  
		+ ")";
		
		try {
			prepStatement = conn.getSQLConexion().prepareStatement(instruction);
			prepStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	private static void createTableDomicilio() {
		String instruction = "CREATE TABLE domicilio("
		+ "  idDomicilio int(10) NOT NULL AUTO_INCREMENT," 
		+ "calle varchar(20),"
		+ "altura int(10)," 
		+ "piso int(10)," 
		+ "depto varchar(20),"  
		+ "idLocalidad int(10),"  
		+ "PRIMARY KEY (idDomicilio),"  
		+ "FOREIGN KEY (idLocalidad) REFERENCES localidad (idLocalidad)"
		+ ")";
		
		try {
			prepStatement = conn.getSQLConexion().prepareStatement(instruction);
			prepStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	private static void createTablePersona() {
		String instruction = "CREATE TABLE persona("
		  + "idPersona int(10) NOT NULL AUTO_INCREMENT,"
		  + "nombre varchar(45) NOT NULL,"
		  + "telefono varchar(20) NOT NULL,"
		  + "PRIMARY KEY (idPersona),"
		  + "idDomicilio int(10),"
		  + "idTipoPersona int(10),"
		  + "FOREIGN KEY (idTipoPersona) REFERENCES tipo_persona  (idTipoPersona),"
		  + "FOREIGN KEY (idDomicilio) REFERENCES domicilio   (idDomicilio)"
		  + ");";

		try {
			prepStatement = conn.getSQLConexion().prepareStatement(instruction);
			prepStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
}
