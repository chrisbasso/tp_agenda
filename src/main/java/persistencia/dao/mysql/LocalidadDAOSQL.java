package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.LocalidadDAO;
import persistencia.dto.DomicilioDTO;
import persistencia.dto.LocalidadDTO;

public class LocalidadDAOSQL implements LocalidadDAO {
	private static final Logger LOGGER = Logger.getLogger(LocalidadDAOSQL.class);
	private static final Conexion conexion = Conexion.getConexion();
	
	private static final String insert = 
			"INSERT INTO localidad" +
			"(nombre_localidad) " +
			 "VALUES(?)";

	private static final String edit = 
			"UPDATE localidad " +
			"SET nombre_localidad = ? " +
			"WHERE idLocalidad = ?;";
	
	private static final String delete = 
			"DELETE FROM localidad " +
			"WHERE idLocalidad = ? ";

	private static final String readall = 
			"SELECT * FROM localidad";
	
	private static final String selectById = 
			"SELECT * FROM localidad " +
			"WHERE idLocalidad = ?";

	public List<LocalidadDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<LocalidadDTO> localidades = new ArrayList<>();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);			
			resultSet = statement.executeQuery();
			LOGGER.info(statement.toString());
			LOGGER.info("Tamaño " + resultSet.getFetchSize());
			while (resultSet.next()) {
				LocalidadDTO nuevaLocalidad = new LocalidadDTO(
						resultSet.getInt("idLocalidad"),
						resultSet.getString("nombre_localidad"));
				localidades.add(nuevaLocalidad);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return localidades;
	}

	public boolean insert(LocalidadDTO localidad) {
		PreparedStatement statement;
		try {
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setString(1, localidad.getNombreLocalidad());
			LOGGER.info(statement.toString());
			if (statement.executeUpdate() > 0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean delete(int idLocalidad) {
		PreparedStatement statement;
		try {
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setString(1,Integer.toString(idLocalidad));
			LOGGER.info(statement.toString());
			if (statement.executeUpdate() > 0) {
				return true;
			}	
		} catch (MySQLIntegrityConstraintViolationException e) {
			JOptionPane.showMessageDialog(null, "La Localidad que intenta eliminar esta relacionada a un contacto, debe eliminar primero el contacto");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}


	public boolean editar(LocalidadDTO localidadDTO) {
		PreparedStatement statement;
		try {
			statement = conexion.getSQLConexion().prepareStatement(edit);
			statement.setString(1, localidadDTO.getNombreLocalidad());
			statement.setInt(2, localidadDTO.getIdLocalidad());
			LOGGER.info(statement.toString());
			if (statement.executeUpdate() > 0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public LocalidadDTO obtenerPorId(int idLocalidad) {
		PreparedStatement statement;
		ResultSet resultSet;
		try {
			statement = conexion.getSQLConexion().prepareStatement(selectById);
			statement.setInt(1, idLocalidad);

			LOGGER.info(statement.toString());
			resultSet = statement.executeQuery();
			LocalidadDTO localidadDTO = new LocalidadDTO(
					resultSet.getString("nombre")
					); 
			
			return localidadDTO;
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		throw new NullPointerException();		
	}

}