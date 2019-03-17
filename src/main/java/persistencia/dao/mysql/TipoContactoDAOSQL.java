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

	private static final String insert = "INSERT INTO tipo_contactos(idTipoContacto, tipoContacto) VALUES(?, ?)";
	private static final String delete = "DELETE FROM tipo_contactos WHERE idtipoContacto = ?";
	private static final String update = "UPDATE tipo_contactos SET tipoContacto = ? WHERE idtipoContacto = ?";
	private static final String readall = "SELECT * FROM tipo_contactos";
	
	@Override
	public boolean insert(TipoContactoDTO tipoContacto) {
		PreparedStatement statement;
		Conexion conexion = Conexion.getConexion();
		
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setInt(1, tipoContacto.getIdTipoContacto());
			statement.setString(2, tipoContacto.getTipo_contacto());
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean edit(TipoContactoDTO tipoContacto) {
		// TODO Auto-generated method stub
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
						resultSet.getString("tipoContacto")));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return tipoContactos;
	}

}
