package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.PersonaDAO;
import dto.PersonaDTO;

public class PersonaDAOSQL implements PersonaDAO
{
	private static final Logger LOGGER = Logger.getLogger(Conexion.class);

	private static final String insert = "INSERT INTO personas(idPersona, nombre, telefono, domicilio) VALUES(?, ?, ?, ?)";
	private static final String delete = "DELETE FROM personas WHERE idPersona = ?";
	private static final String readall = "SELECT * FROM personas";

	public boolean insert(PersonaDTO persona)
	{
		PreparedStatement statement;
		Conexion conexion = Conexion.getConexion();
		try
		{
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setInt(1, persona.getIdPersona());
			statement.setString(2, persona.getNombre());
			statement.setString(3, persona.getTelefono());
			if(statement.executeUpdate() > 0){
				LOGGER.info(statement.toString());
				return true;
			}

		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return false;
	}

	public boolean delete(PersonaDTO persona_a_eliminar)
	{
		PreparedStatement statement;
		int chequeoUpdate = 0;
		Conexion conexion = Conexion.getConexion();
		try
		{
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setString(1, Integer.toString(persona_a_eliminar.getIdPersona()));
			chequeoUpdate = statement.executeUpdate();
			LOGGER.info(statement.toString());
			if(chequeoUpdate > 0) //Si se ejecutó devuelvo true
				return true;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}

	public List<PersonaDTO> readAll()
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<PersonaDTO> personas = new ArrayList<PersonaDTO>();
		Conexion conexion = Conexion.getConexion();
		try
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			LOGGER.info(statement.toString());

			while(resultSet.next())
			{
				personas.add(new PersonaDTO(resultSet.getInt("idPersona"),
						resultSet.getString("Nombre"),
						resultSet.getString("Telefono"),
						resultSet.getString("calle"),
						resultSet.getString("altura"),
						resultSet.getString("piso"),
						resultSet.getString("depto"),
						resultSet.getString("localidad"),
						resultSet.getString("tipoDomicilio")));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return personas;
	}
}