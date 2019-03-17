package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.ContactoDAO;
import dto.ContactoDTO;

public class ContactoDAOSQL implements ContactoDAO
{
	private static final Logger LOGGER = Logger.getLogger(Conexion.class);

	private static final String readall = "SELECT p.idPersona,p.nombre,p.telefono,dom.idDomicilio,dom.calle," +
			"dom.altura,dom.piso, dom.depto,loc.idLocalidad,loc.nombre_localidad,tipo.idTipoPersona,tipo.tipo " +
			"FROM persona p " +
			"JOIN domicilio dom ON p.idDomicilio = dom.idDomicilio " +
			"JOIN tipo_persona tipo ON p.idTipoPersona = tipo.idTipoPersona " +
			"JOIN localidad loc ON dom.idLocalidad = loc.idLocalidad";

	public List<ContactoDTO> readAll()
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<ContactoDTO> contactos = new ArrayList<ContactoDTO>();
		Conexion conexion = Conexion.getConexion();
		try
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			LOGGER.info(statement.toString());

			while(resultSet.next())
			{
				contactos.add(new ContactoDTO(resultSet.getInt("idPersona"),
						resultSet.getString("nombre"),
						resultSet.getString("telefono")
						,resultSet.getString("calle"),
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
		return contactos;
	}
}