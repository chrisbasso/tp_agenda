package persistencia.dao.interfaz;

import java.util.List;

import persistencia.dto.PersonaDTO;

public interface PersonaDAO
{
	public boolean insert(PersonaDTO persona);

	public boolean delete(int idPersona);

	public List<PersonaDTO> readAll();

	boolean editar(PersonaDTO nuevaPersona);
}
