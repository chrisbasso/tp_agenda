package deployment;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import dto.DomicilioDTO;
import dto.LocalidadDTO;
import dto.PersonaDTO;
import dto.TipoContactoDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.LocalidadDAO;
import persistencia.dao.interfaz.PersonaDAO;
import persistencia.dao.interfaz.TipoContactoDAO;
import persistencia.dao.mysql.LocalidadDAOSQL;
import persistencia.dao.mysql.PersonaDAOSQL;
import persistencia.dao.mysql.TipoContactoDAOSQL;

public class Mysql {
	
	private static Conexion conn = Conexion.getConexion();
	private static PreparedStatement prepStatement;
	
	public static void start() {
		createTables();
	}
	
	public static void loadInitialData() throws SQLException {
		
		LocalidadDTO localidad = new LocalidadDTO(1, "San Miguel");
		DomicilioDTO domicilio = new DomicilioDTO(1, "Callao", "456","0","",localidad);
		TipoContactoDTO tipoContacto = new TipoContactoDTO(1, "Cliente");
		PersonaDTO persona = new PersonaDTO("Ricardo", "113434", domicilio, tipoContacto);
		
		LocalidadDAO localidadDao = new LocalidadDAOSQL();
		localidadDao.insert(localidad.getNombreLocalidad());
		
				
		TipoContactoDAO tipoContactoDao = new TipoContactoDAOSQL();
		tipoContactoDao.insert(tipoContacto);
				
		PersonaDAO personaDao = new PersonaDAOSQL();
		personaDao.insert(persona);
		
	}
	
	private static void createTables() {
		createTableTipoPersona();
		createTableLocalidad();		
		createTableDomicilio();
		createTablePersona();		 
	}

	private static void createTableTipoPersona() {
		String instruction = "CREATE TABLE tipo_contacto("
		+ "idTipoContacto int(10) NOT NULL AUTO_INCREMENT,"
		+ "tipo varchar(20),"
		+ "PRIMARY KEY (idTipoContacto)"
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
		  + "idTipoContacto int(10),"
		  + "FOREIGN KEY (idTipoContacto) REFERENCES tipo_contacto  (idTipoContacto),"
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
