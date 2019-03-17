package persistencia.dao.interfaz;

import dto.ContactoDTO;
import modelo.Persona;

import java.util.List;

public interface PersonaDAO
{

	public boolean insert(Persona persona);

	public boolean delete(Persona persona_a_eliminar);

	public List<Persona> readAll();
}
