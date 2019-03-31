package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import dto.DomicilioDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.DomicilioDAO;

public class DomicilioDAOSQL implements DomicilioDAO {
	private static final Logger LOGGER = Logger.getLogger(Conexion.class);
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

	public boolean insert(DomicilioDTO domicilio) {
		PreparedStatement statement;
		try {
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setString(1, domicilio.getCalle());
			statement.setString(2, domicilio.getAltura());
			statement.setString(3, domicilio.getPiso());
			statement.setString(4, domicilio.getDepto());
			statement.setInt(5, domicilio.getLocalidad().getIdLocalidad());
			
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
			statement.setInt(5, domicilio.getLocalidad().getIdLocalidad());
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
}
