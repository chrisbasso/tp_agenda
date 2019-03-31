package persistencia.dao.mysql;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.TipoContactoDTO;
import org.apache.log4j.Logger;
import persistencia.conexion.Conexion;
import dto.DomicilioDTO;
import dto.LocalidadDTO;
import dto.PersonaDTO;
import persistencia.dao.interfaz.PersonaDAO;

public class PersonaDAOSQL implements PersonaDAO{
	private static final Conexion conexion = Conexion.getConexion();
	private static final Logger LOGGER = Logger.getLogger(Conexion.class);

	private static final String readall = 
			"SELECT p.idPersona,p.nombre,p.apellido,p.telefono,dom.idDomicilio,dom.calle," +
			"dom.altura,dom.piso, dom.depto,loc.idLocalidad,loc.nombre_localidad," + 
			"tipo.idTipoContacto,tipo.tipo, p.email, p.fecha_nacimiento " +
			"FROM persona p " +
			"JOIN domicilio dom ON p.idDomicilio = dom.idDomicilio " +
			"JOIN tipo_contacto tipo ON p.idTipoContacto = tipo.idTipoContacto " +
			"JOIN localidad loc ON dom.idLocalidad = loc.idLocalidad";
	
	private static final String insert = 
			"INSERT INTO persona" +
			"(nombre, apellido, telefono, idDomicilio, idTipoContacto, email, fecha_nacimiento) " +
			"VALUES( ?, ?, ?, ?, ?, ?, ?)";
	
	private static final String delete = 
			"DELETE FROM persona" +
			"WHERE idPersona = ?";
	
	public boolean insert(PersonaDTO persona) {
		PreparedStatement statement;	
		try	{
			int idDomicilio = this.insertarDomicilio(persona.getDomicilio());
			persona.getDomicilio().setIdDomicilio(idDomicilio);

			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setString(1, persona.getNombre());
			statement.setString(2, persona.getApellido());
			statement.setString(3, persona.getTelefono());
			statement.setInt(4,persona.getDomicilio().getIdDomicilio());
			statement.setInt(5, persona.getTipo_Persona().getIdTipoContacto());
			statement.setString(6, persona.getEmail());
			statement.setDate(7, new Date(persona.getFechaNacimiento().getTime()));
			
			if(statement.executeUpdate() > 0){
				LOGGER.info(statement.toString());
				return true;
			}
		}catch (SQLException e){
			e.printStackTrace();
		}
		return false;
	}

	private int insertarDomicilio(DomicilioDTO domicilio) {
		PreparedStatement statement;
		Conexion conexion = Conexion.getConexion();
		try {
			String query = 
					"INSERT INTO " +
					"domicilio (calle, altura, piso, depto, idLocalidad) " +
					"values (?,?,?,?,?)";
			statement = conexion.getSQLConexion().prepareStatement(query);
			statement.setString(1, domicilio.getCalle());
			statement.setString(2, domicilio.getAltura());
			statement.setString(3, domicilio.getPiso());
			statement.setString(4, domicilio.getDepto());
			statement.setInt(5, domicilio.getLocalidad().getIdLocalidad());

			if(statement.executeUpdate() > 0){
				LOGGER.info(statement.toString());
			}

			int idDomicilio = this.getIdUltimoDomicilio();
			domicilio.setIdDomicilio(idDomicilio);

			return idDomicilio;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public int getIdLocalidad(String localidad) {
		String query = 
				"SELECT idLocalidad " + 
				"FROM localidad " +
				"WHERE nombre_localidad = (?)";

		PreparedStatement statement;
		ResultSet resultSet;
		try {
			statement = conexion.getSQLConexion().prepareStatement(query);
			statement.setString(1, localidad);

			System.out.println(statement.toString());

			resultSet = statement.executeQuery();
			int resultado = -1;

			while (resultSet.next()) {
				resultado = resultSet.getInt("IdLocalidad");
			}

			if (resultado != -1)
				return resultado;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public int getIdUltimoDomicilio() {
		String query = 
				"SELECT MAX(idDomicilio) " +
				" FROM domicilio";

		PreparedStatement statement;
		Conexion conexion = Conexion.getConexion();
		ResultSet resultSet;
		try {
			statement = conexion.getSQLConexion().prepareStatement(query);
			resultSet = statement.executeQuery();
			int resultado = -1;

			while (resultSet.next()) {
				resultado = resultSet.getInt(1);
			}

			if (resultado != -1)
				return resultado;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public boolean delete(PersonaDTO persona_a_eliminar) {
		PreparedStatement statement;
		int chequeoUpdate = 0;
		try {
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setString(1,
					Integer.toString(persona_a_eliminar.getIdPersona()));
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
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			LOGGER.info(statement.toString());

			while (resultSet.next()) {
				LocalidadDTO localidad = new LocalidadDTO(
						resultSet.getInt("idLocalidad"),
						resultSet.getString("nombre_localidad"));

				DomicilioDTO domicilio = new DomicilioDTO(
						resultSet.getInt("idDomicilio"),
						resultSet.getString("calle"),
						resultSet.getString("altura"),
						resultSet.getString("piso"),
						resultSet.getString("depto"),
						localidad);

				TipoContactoDTO tipoContacto = new TipoContactoDTO(
						resultSet.getInt("idTipoContacto"),
						resultSet.getString("tipo"));

				personas.add(new PersonaDTO(
						resultSet.getInt("idPersona"),
						resultSet.getString("nombre"),
						resultSet.getString("apellido"),
						resultSet.getString("telefono"),
						domicilio,
						tipoContacto,
						resultSet.getString("email"),
						resultSet.getDate("fecha_nacimiento")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return personas;
	}

	public boolean editar(PersonaDTO persona) {
		int idLocalidad = persona.getDomicilio().getLocalidad().getIdLocalidad();
		int idDomicilio = persona.getDomicilio().getIdDomicilio();

		String queryDomicilio = 
				"UPDATE domicilio "
				+ "SET calle = ?, altura = ?, piso = ?, depto = ?, idLocalidad = ? +"
				+ "WHERE idDomicilio = ?;";
		String queryPersona = 
				"UPDATE persona " +
				"SET nombre = ?, telefono = ?, idDomicilio = ?, idTipoContacto = ?,email = ?, fecha_nacimiento = ? " +
				"WHERE idPersona = ?;";
		PreparedStatement statement;

		try {
			statement = conexion.getSQLConexion().prepareStatement(queryDomicilio);
			statement.setString(1, persona.getDomicilio().getCalle());
			statement.setString(2, persona.getDomicilio().getAltura());
			statement.setString(3, persona.getDomicilio().getPiso());
			statement.setString(4, persona.getDomicilio().getDepto());
			statement.setInt(5, idLocalidad);
			statement.setInt(6, idDomicilio);
			statement.executeUpdate();
			LOGGER.info(statement.toString());

		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			statement = conexion.getSQLConexion().prepareStatement(queryPersona);
			statement.setString(1, persona.getNombre());
			statement.setString(2, persona.getApellido());
			statement.setString(3, persona.getTelefono());
			statement.setInt(4, idDomicilio);
			statement.setInt(5, persona.getTipo_Persona().getIdTipoContacto());
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