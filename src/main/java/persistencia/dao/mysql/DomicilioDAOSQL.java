package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.DomicilioDAO;
import persistencia.dto.DomicilioDTO;
import persistencia.dto.PersonaDTO;

public class DomicilioDAOSQL implements DomicilioDAO {
	private static final Logger LOGGER = Logger.getLogger(DomicilioDAOSQL.class);
	private static final Conexion conexion = Conexion.getConexion();
	
	private static final String insert = 
			"INSERT INTO domicilio " +
			"(calle, altura, piso, depto, idLocalidad) " +
			"VALUES(?, ? ,?, ?, ?)";
	
	private static final String delete = 
			"DELETE FROM domicilio " +
			"WHERE idDomicilio = ? ";
	
	private static final String edit = 
			"UPDATE domicilio SET (calle, altura, piso, depto, idLocalidad) " +
			"VALUES (?, ? ,?, ?, ?) " +
			"WHERE idDomicilio = ?";
	
	private static final String readAll = 
			"SELECT * FROM domicilio ";

	private static final String selectById = 
			"SELECT * FROM domicilio " +
			"WHERE idDomicilio = ?";

	public boolean insert(DomicilioDTO domicilio) {
		PreparedStatement statement;
		try {
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setString(1, domicilio.getCalle());
			statement.setString(2, domicilio.getAltura());
			statement.setString(3, domicilio.getPiso());
			statement.setString(4, domicilio.getDepto());
			statement.setInt(5, domicilio.getIdLocalidad());
			
			if (statement.executeUpdate() > 0) {
				return true;
			}				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean delete(int idDomicilio) {
		PreparedStatement statement;
		try {
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setInt(1, idDomicilio);
			if (statement.executeUpdate() > 0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean editar(DomicilioDTO domicilio) {
		PreparedStatement statement;
		try {
			statement = conexion.getSQLConexion().prepareStatement(edit);
			statement.setString(1, domicilio.getCalle());
			statement.setString(2, domicilio.getAltura());
			statement.setString(3, domicilio.getPiso());
			statement.setString(4, domicilio.getDepto());
			statement.setInt(5, domicilio.getIdLocalidad());
			statement.setInt(6, domicilio.getIdDomicilio());
			LOGGER.info(statement.toString());
			if (statement.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public List<DomicilioDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<DomicilioDTO> domicilios = new ArrayList<>();
		
		try {
			statement = conexion.getSQLConexion().prepareStatement(readAll);
			resultSet = statement.executeQuery();
			LOGGER.info(statement.toString());

			while (resultSet.next()) {				
				domicilios.add(new DomicilioDTO(
						resultSet.getString("calle"),
						resultSet.getString("altura"),
						resultSet.getString("piso"),
						resultSet.getString("depto"),
						resultSet.getInt("idLocalidad")
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return domicilios;
	}

	
	public int idDomicilio(DomicilioDTO domicilio) {
		PreparedStatement statement;
		ResultSet resultSet;
		try {
			statement = conexion.getSQLConexion().prepareStatement(readAll);
			statement.setString(1, domicilio.getCalle());
			statement.setString(2, domicilio.getAltura());
			statement.setString(3, domicilio.getPiso());
			statement.setString(4, domicilio.getDepto());
			statement.setInt(5, domicilio.getIdLocalidad());

			LOGGER.info(statement.toString());
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				if(resultSet.getString("calle").equals(domicilio.getCalle()) &&
						resultSet.getString("altura").equals(domicilio.getAltura()) &&
						resultSet.getString("piso").equals(domicilio.getPiso()) &&
						resultSet.getString("depto").equals(domicilio.getDepto()) &&
						resultSet.getInt("idlocalidad") == domicilio.getIdLocalidad()) {
					return resultSet.getInt("idDomicilio");
				}						
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		throw new NullPointerException();
	}
	
	public DomicilioDTO obtenerPorId(int idDomicilio) {
		PreparedStatement statement;
		ResultSet resultSet;
		try {
			statement = conexion.getSQLConexion().prepareStatement(selectById);
			statement.setInt(1, idDomicilio);

			LOGGER.info(statement.toString());
			resultSet = statement.executeQuery();
			DomicilioDTO domicilioDTO = new DomicilioDTO(
					resultSet.getString("calle"),
					resultSet.getString("altura"),
					resultSet.getString("piso"),
					resultSet.getString("depto"),
					resultSet.getInt("idlocalidad")
					); 
			
			return domicilioDTO;
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		throw new NullPointerException();		
	}
}
