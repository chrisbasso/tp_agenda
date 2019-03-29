package persistencia.dao.mysql;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import dto.LocalidadDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.LocalidadDAO;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

public class DomicilioDAOSQL implements LocalidadDAO {
	private static final Logger LOGGER = Logger.getLogger(Conexion.class);

	private static final String insert = "INSERT INTO localidad(nombre_localidad) VALUES(?)";
	private static final String readall = "SELECT * FROM localidad";
	private static final String delete = " DELETE FROM localidad WHERE idLocalidad = ? ";
	private static final String edit = "UPDATE localidad SET nombre_localidad = ? WHERE idLocalidad = ?;";
	private static final Conexion conexion = Conexion.getConexion();

	public List<LocalidadDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<LocalidadDTO> localidades = new ArrayList<>();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();

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



	public boolean insert(String localidad) {

		PreparedStatement statement;
		try {
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setString(1, localidad);
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
			statement.setInt(1, idLocalidad);
			if (statement.executeUpdate() > 0)
				return true;
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
}
