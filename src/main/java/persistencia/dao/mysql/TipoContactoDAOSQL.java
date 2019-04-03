package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.apache.log4j.Logger;

import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.TipoContactoDAO;
import persistencia.dto.DomicilioDTO;
import persistencia.dto.TipoContactoDTO;

import javax.swing.*;

public class TipoContactoDAOSQL implements TipoContactoDAO {	
	private static final Logger LOGGER = Logger.getLogger(TipoContactoDAOSQL.class);
	private static Conexion conexion = Conexion.getConexion();
	
	private static final String insert = 
			"INSERT INTO tipo_contacto(idTipoContacto, tipo)"+
			"VALUES(?, ?)";
	
	private static final String delete = 
			"DELETE FROM tipo_contacto " +
			"WHERE idTipoContacto = ?";
	
	private static final String update = 
			"UPDATE tipo_contacto " +
			"SET tipo = ? " +
			"WHERE idTipoContacto = ?";
	
	private static final String readall = 
			"SELECT * FROM tipo_contacto";
	
	private static final String selectById = 
			"SELECT * FROM tipo_contacto " +
			"WHERE idTipoContacto = ?";
	
	@Override
	public boolean insert(TipoContactoDTO tipoContacto) {
		PreparedStatement statement;	
		
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
	public boolean delete(int idTipoContacto){
		PreparedStatement statement;
		
		try {
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setInt(1, idTipoContacto);
			if (statement.executeUpdate() > 0)
				return true;
		} catch (MySQLIntegrityConstraintViolationException e){
			JOptionPane.showMessageDialog(null, "El tipo de contacto que intenta eliminar esta relacionado a un contacto, debe eliminar primero el contacto");
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
				tipoContactos.add(new TipoContactoDTO(resultSet.getInt("idTipoContacto"),
						resultSet.getString("tipo")));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return tipoContactos;
	}
	
	public TipoContactoDTO obtenerPorId(int idTipoContacto) {
		PreparedStatement statement;
		ResultSet resultSet;
		try {
			statement = conexion.getSQLConexion().prepareStatement(selectById);
			statement.setInt(1, idTipoContacto);

			LOGGER.info(statement.toString());
			resultSet = statement.executeQuery();
			TipoContactoDTO tipoContactoDTO = new TipoContactoDTO(
					resultSet.getInt("idTipoContacto"),
					resultSet.getString("tipo")
					); 
			
			return tipoContactoDTO;
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		throw new NullPointerException();		
	}
}
