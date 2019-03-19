package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import dto.TipoContactoDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.TipoContactoDAO;

public class TipoContactoDAOSQL implements TipoContactoDAO {
	
	private static final Logger LOGGER = Logger.getLogger(Conexion.class);

	private static final String insert = "INSERT INTO tipo_persona(idTipoPersona, tipo) VALUES(?, ?)";
	private static final String delete = "DELETE FROM tipo_persona WHERE idTipoPersona = ?";
	private static final String update = "UPDATE tipo_persona SET tipo = ? WHERE idTipoPersona = ?";
	private static final String readall = "SELECT * FROM tipo_persona";
	//private static final String readbyid = "SELECT";
	
	@Override
	public boolean insert(TipoContactoDTO tipoContacto) {
		PreparedStatement statement;
		Conexion conexion = Conexion.getConexion();
		
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setInt(1, tipoContacto.getIdTipoContacto());
			statement.setString(2, tipoContacto.getTipoContacto());
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

	@Override
	public boolean delete(TipoContactoDTO tipoContacto_a_eliminar) {
		PreparedStatement statement;
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setString(1, Integer.toString(tipoContacto_a_eliminar.getIdTipoContacto()));
			if (statement.executeUpdate() > 0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean edit(TipoContactoDTO tipoContacto_a_editar) {
		PreparedStatement statement;
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(update);
			statement.setString(1, tipoContacto_a_editar.getTipoContacto());
			statement.setString(2, Integer.toString(tipoContacto_a_editar.getIdTipoContacto()));
			if(statement.executeUpdate() > 0) {
				return true;
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<TipoContactoDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<TipoContactoDTO> tipoContactos = new ArrayList<TipoContactoDTO>();

		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			LOGGER.info(statement.toString());
			
			while(resultSet.next())
			{
				tipoContactos.add(new TipoContactoDTO(resultSet.getInt("idTipoPersona"),
						resultSet.getString("tipo")));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return tipoContactos;
	}

}
