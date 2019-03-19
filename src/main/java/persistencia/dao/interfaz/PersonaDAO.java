package persistencia.dao.interfaz;

import dto.PersonaDTO;

import java.sql.SQLException;
import java.util.List;

public interface PersonaDAO
{
	public boolean insert(PersonaDTO persona) throws SQLException;

	public boolean delete(PersonaDTO persona_a_eliminar);

	public List<PersonaDTO> readAll();

	boolean editar(PersonaDTO nuevaPersona) throws SQLException;
}
