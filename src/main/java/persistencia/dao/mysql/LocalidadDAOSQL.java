package persistencia.dao.mysql;

import dto.LocalidadDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.LocalidadDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LocalidadDAOSQL implements LocalidadDAO {

	private static final String insert = "INSERT INTO localidad(nombre_localidad) VALUES(?)";
	private static final String readall = "SELECT * FROM localidad";
	private static final String delete = " ";
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

	public boolean existe(String localidad) {
		List<LocalidadDTO> localidades = this.readAll();

		for(LocalidadDTO localidad2 : localidades)
		{
			if (localidad2.getNombreLocalidad() == localidad){
				return true;
			}
		}
		return false;
	}

	public boolean insert(String localidad) {
		if (!this.existe(localidad)) {
			PreparedStatement statement;
			try {
				statement = conexion.getSQLConexion().prepareStatement(insert);
				statement.setString(1, localidad);
				if (statement.executeUpdate() > 0)
					// true
					return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public int getIdLocalidad(String localidad) {
		String query = "SELECT idLocalidad "
				+ "FROM localidad "
				+ "WHERE nombre_localidad = ? ";

		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		try {

			statement = conexion.getSQLConexion().prepareStatement(query);
			statement.setString(1, localidad);

			System.out.println(statement.toString());

			resultSet = statement.executeQuery();
			int resultado = -1;

			while(resultSet.next())
			{
				resultado = resultSet.getInt("IdLocalidad");
			}

			if (resultado != -1)
				return resultado;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;

	}

	public boolean editar(LocalidadDTO localidadDTO) {
		if (!this.existe(localidadDTO.getNombreLocalidad()))
		{
			int id1 = this.getIdLocalidad(String.valueOf(localidadDTO.getIdLocalidad()));

			PreparedStatement statement;
			try {
				statement = conexion.getSQLConexion().prepareStatement(edit);
				statement.setString(1, localidadDTO.getNombreLocalidad());
				statement.setInt(2, localidadDTO.getIdLocalidad());
				if (statement.executeUpdate() > 0)
					return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;

	}
}
