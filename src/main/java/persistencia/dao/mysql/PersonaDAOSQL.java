package persistencia.dao.mysql;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.PersonaDAO;
import persistencia.dto.PersonaDTO;

public class PersonaDAOSQL implements PersonaDAO{
	private static final Conexion conexion = Conexion.getConexion();
	private static final Logger LOGGER = Logger.getLogger(PersonaDAOSQL.class);

	private static final String readall = 
			"SELECT * FROM persona";
	
	private static final String insert = 
			"INSERT INTO persona" +
			"(nombre, apellido, telefono, idDomicilio, idTipoContacto, email, fecha_nacimiento) " +
			"VALUES( ?, ?, ?, ?, ?, ?, ?)";

	private static final String edit = 
			"UPDATE persona SET (nombre, apellido, telefono, idDomicilio, idTipoContacto, email, fecha_nacimiento) " +
			"VALUES (?, ?, ?, ?, ?, ?, ?) " +
			"WHERE idPersona = ?";

	private static final String delete = 
			"DELETE FROM persona" +
			"WHERE idPersona = ?";
	
	public boolean insert(PersonaDTO persona) {
		PreparedStatement statement;	
		try	{
			
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setString(1, persona.getNombre());
			statement.setString(2, persona.getApellido());
			statement.setString(3, persona.getTelefono());
			statement.setInt(4,persona.getIdDomicilio());
			statement.setInt(5, persona.getIdTipoContacto());
			statement.setString(6, persona.getEmail());
			statement.setDate(7, new Date(persona.getFechaNacimiento().getTime()));
			LOGGER.info(statement.toString());			
			
			if(statement.executeUpdate() > 0)
				return true;
		}catch (SQLException e){
			e.printStackTrace();
		}
		return false;
	}

	public boolean delete(int idPersona) {
		PreparedStatement statement;
		int chequeoUpdate = 0;
		try {
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setString(1,Integer.toString(idPersona));
			chequeoUpdate = statement.executeUpdate();
			if (chequeoUpdate > 0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<PersonaDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<PersonaDTO> personas = new ArrayList<>();
		
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			LOGGER.info(statement.toString());
			LOGGER.info("Tamaño " + resultSet.getFetchSize());
			while (resultSet.next()) {				
				personas.add(new PersonaDTO(
						resultSet.getInt("idPersona"),
						resultSet.getString("nombre"),
						resultSet.getString("apellido"),
						resultSet.getString("telefono"),
						resultSet.getInt("idDomicilio"),
						resultSet.getInt("idTipoContacto"),						
						resultSet.getString("email"),
						resultSet.getDate("fecha_nacimiento")
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return personas;
	}

	public boolean editar(PersonaDTO persona) {
		PreparedStatement statement;
		try {
			statement = conexion.getSQLConexion().prepareStatement(edit);
			statement.setString(1, persona.getNombre());
			statement.setString(2, persona.getApellido());
			statement.setString(3, persona.getTelefono());
			statement.setInt(4, persona.getIdDomicilio());
			statement.setInt(5, persona.getIdTipoContacto());
			statement.setString(6, persona.getEmail());
			statement.setDate(7,new Date(persona.getFechaNacimiento().getTime()));
			statement.setInt(8, persona.getIdPersona());

			LOGGER.info(statement.toString());
			if (statement.executeUpdate() > 0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}